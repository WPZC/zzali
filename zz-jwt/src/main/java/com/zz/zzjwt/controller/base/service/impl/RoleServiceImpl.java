package com.zz.zzjwt.controller.base.service.impl;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.RoleMenu;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.methods.Backtrack;
import com.zz.region.vo.view.Node;
import com.zz.region.vo.view.RoleMenuView;
import com.zz.region.vo.view.TreeEntity;
import com.zz.zzjwt.controller.base.service.RoleService;
import com.zz.zzsystemapi.service.MenuMangementFegin;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import com.zz.zzsystemapi.service.UserMangementFegin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 15:13
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMangementFegin roleMangementFegin;

    @Resource
    private MenuMangementFegin menuMangementFegin;

    @Resource
    private UserMangementFegin userMangementFegin;

    @Override
    public RoleEntity addRole(String name, String description) {

        return Backtrack.checkData(roleMangementFegin.addRole(name,description));

    }

    @Override
    public int roleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public String deleteByRId(Long rId) {
        return Backtrack.checkMsg(roleMangementFegin.deleteByRId(rId));
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        return Backtrack.checkData(roleMangementFegin.updateRole(roleEntity));
    }

    @Override
    public int updateRoleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public PageData<RoleEntity> getRoleMsgs(Integer currentPage) {

        PageData<RoleEntity> list = Backtrack.checkData(roleMangementFegin.getRoleMsgs(currentPage));

        return list;
    }

    @Override
    public TreeEntity getNodes(Long rId) {

        //获取菜单与角色的绑定关系
        List<RoleMenu> roleMenus = Backtrack.checkData(roleMangementFegin.getRoleMenus(rId));
        //获取所有菜单
        List<Menu> menus = Backtrack.checkData(menuMangementFegin.getMenus());
        Map<Long,Long> map = roleMenus.stream().collect(Collectors.toMap(RoleMenu::getMId, RoleMenu::getRId));

        //生成“，”分隔的字符串，用于是否选中判断
        String str = "";
        for (int i = 0,num = menus.size();i<num;i++){
            str = str + menus.get(i).getMenuCode() +",";
        }
        //被选中的
        List<Long> checked = new ArrayList<>();
        //验证子节点是否被选中
        String finalStr = str;
        menus.stream().forEach(menu -> {
            if(map.get(menu.getId())!=null&&menu.getMenuCode().length()>4){
                //判断当前menu是否含有子集,含有则未选中
                if(!finalStr.contains(menu.getMenuCode()+"0")){
                    checked.add(menu.getId());
                }
            }
        });

        //将menus装成tree对应格式
        List<Node> nodes = new ArrayList<>();
        //找到根节点，将根节点添加
        Node node = new Node();
        menusf:for (Menu menu:menus){
            if(menu.getMenuCode().length()==4){
                node.setChildren(new ArrayList<>());
                node.setCode(menu.getMenuCode());
                node.setLabel(menu.getMenuName());
                node.setId(menu.getId());
                break menusf;
            }
        }
        //将根节点和数据源传入
        menuConversion(menus,node);
        nodes.add(node);

        return TreeEntity
                .builder()
                .checked(checked)
                .nodes(nodes)
                .build();
    }

    @Override
    public String saveNodes(Long rId, List<String> menuIds) {

        if(rId==null){
            return "角色ID为空";
        }

        List<RoleMenu> roleMenus = new ArrayList<>();

        menuIds.stream().forEach(m->{
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMId(Long.parseLong(m));
            roleMenu.setRId(rId);
            roleMenus.add(roleMenu);
        });

        return Backtrack.checkMsg(roleMangementFegin.saveNodes(roleMenus));
    }

    @Override
    public List<RoleEntity> getRoleMsgsList() {
        return Backtrack.checkData(roleMangementFegin.getRoleMsgsList());
    }

    @Override
    public List<RoleMenuView> getRoleMenuMsg(HttpServletRequest request) {

        List<Menu> menus = Backtrack.checkData(roleMangementFegin.getMenuByRId(userMangementFegin.findByUserName(request.getHeader("token")).getId()));
        List<RoleMenuView> rs = new ArrayList<>();
        //由于只有两级菜单
        for (int i = 0,num=menus.size();i<num;i++){
            Menu menu = menus.get(i);
            //一级菜单为8长度
            if(menu.getMenuCode().length()==8){
                RoleMenuView roleMenuView = new RoleMenuView();
                roleMenuView.setImgSrc(menu.getImgsrc());
                roleMenuView.setTitle(menu.getMenuName());
                roleMenuView.setMenus(new ArrayList<>());
                roleMenuView.setIndex(menu.getId()+"");
                roleMenuView.setRouter(menu.getRouter());
                for (int j = 0,numj=menus.size();j<numj;j++){
                    //判断是否是menu的子菜单
                    Menu menuj = menus.get(j);
                    //长度是否相等
                    if((menuj.getMenuCode().length())==(menu.getMenuCode().length()+4)&&menuj.getMenuCode().length()>8){
                        //是否是该菜单的子集
                        if(menuj.getMenuCode().substring(0,menu.getMenuCode().length()).equals(menu.getMenuCode())){
                            menuj.setIndex(menu.getId()+"-"+menuj.getId());
                            roleMenuView.getMenus().add(menuj);
                            roleMenuView.setIsSsons(true);
                        }
                    }
                }
                rs.add(roleMenuView);
            }
        }

        return rs;
    }

    /**
     * 递归遍历菜单
     * @param menus
     * @param node
     */
    void menuConversion(List<Menu> menus, Node node){

        for (int i = 0,num = menus.size();i<num;i++){
            Menu menu = menus.get(i);
            //判断是否是该节点的子集,长度是否符合
            if (menu.getMenuCode().length()==node.getCode().length()+4
                    &&menu.getMenuCode().substring(0,node.getCode().length())
                    .equals(node.getCode())){
                //是子集则添加
                Node n = new Node();
                n.setId(menu.getId());
                n.setLabel(menu.getMenuName());
                n.setCode(menu.getMenuCode());
                n.setChildren(new ArrayList<>());
                node.getChildren().add(n);
                menuConversion(menus,n);
            }

        }
    }
}
