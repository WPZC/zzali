package com.zz.zzbaseapi.base.service.impl;

import com.zz.domain.PageData;
import com.zz.domain.authority.Menu;
import com.zz.domain.authority.Role;
import com.zz.domain.authority.RoleMenu;
import com.zz.jpatemplate.service.BaseService;
import com.zz.security.utils.jwt.JwtUtil;
import com.zz.vo.view.Node;
import com.zz.vo.view.RoleMenuView;
import com.zz.vo.view.TreeEntity;
import com.zz.zzbaseapi.repository.MenuJpa;
import com.zz.zzbaseapi.repository.RoleJpa;
import com.zz.zzbaseapi.repository.RoleMenuJpa;
import com.zz.zzbaseapi.repository.UserJpa;
import com.zz.zzbaseapi.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
public class RoleServiceImpl extends BaseService<Role, RoleJpa> implements RoleService {

    @Autowired
    private UserJpa userJpa;
    @Autowired
    private RoleJpa roleJpa;
    @Autowired
    private MenuJpa menuJpa;

    @Autowired
    private RoleMenuJpa roleMenuJpa;

    public RoleServiceImpl(RoleJpa roleJpa) {
        super(roleJpa);
    }


    @Override
    public Role addRole(String name, String description) {

        Role role = new Role();

        role.setName(name);
        role.setDescription(description);

        return roleJpa.save(role);

    }

    @Override
    public int roleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public String deleteByRId(Long rId) {
        //查询改角色是否有用户绑定,如果有绑定的则不删除并返回错误信息
        int binds = roleJpa.findByUserRoleBind(rId);
        if(binds>0){
            return "请先接触用户与该角色的绑定";
        }
        //删除该角色
        roleJpa.deleteByRId(rId);

        return "操作成功";
    }

    @Override
    public Role updateRole(Role role) {
        return roleJpa.save(role);
    }

    @Override
    public int updateRoleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public PageData<Role> getRoleMsgs(Integer currentPage) {
        return findPages(currentPage,5);
    }

    @Override
    public TreeEntity getNodes(Long rId) {

        //获取菜单与角色的绑定关系
        List<RoleMenu> roleMenus =roleMenuJpa.findByRId(rId);
        //获取所有菜单
        List<Menu> menus = menuJpa.findAll();
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

        try {
            List<RoleMenu> menus = roleMenuJpa.findByRId(roleMenus.get(0).getRId());
            //判断是否已存在菜单，存在则删除
            if(null==menus||menus.size()<=0){
                roleMenuJpa.saveAll(roleMenus);
            }else{
                //删除菜单
                roleMenuJpa.deleteAll(menus);
                //保存菜单
                roleMenuJpa.saveAll(roleMenus);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "操作成功";
    }

    @Override
    public List<Role> getRoleMsgsList() {
        return roleJpa.findAll();
    }

    @Override
    public List<RoleMenuView> getRoleMenuMsg(HttpServletRequest request) {

        List<Menu> menus = menuJpa.getMenuByRId(userJpa.findByUsername(JwtUtil.parseToken(request.getHeader("token")).getUsername()).getId());
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
                            menuj.setIndexNum(menu.getId()+"-"+menuj.getId());
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

    @Override
    public List<Role> findByUserRole(Long id) {
        return roleJpa.findByUserRole(id);
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
