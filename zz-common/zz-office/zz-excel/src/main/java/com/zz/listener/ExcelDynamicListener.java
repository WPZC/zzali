package com.zz.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zz.entity.BaseAnalysis;
import com.zz.region.methods.utils.StringUtil;

import java.util.*;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/16 15:38
 */
public abstract class ExcelDynamicListener extends AnalysisEventListener<HashMap<Integer,String>> {

    /**
     * 每隔200条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 200;
    /**
     * 表名
     */
    private String tableName;

    /**
     * 总字段数
     */
    private Integer total = 0;

    public ExcelDynamicListener(String tableName){
        this.tableName = tableName;
    }
    /**
     * 参数区
     */
    private List<HashMap<Integer,String>> list = new ArrayList<>();


    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(HashMap<Integer,String> data, AnalysisContext context) {

        //验证是否为表头，如果为表头，则与数据库对比
        //total为0则为头部信息
        if(total==0){
            //校验title
            Boolean b = checkTile(data,tableName);
            //true校验成功
            if(b){
                total = data.size();
                return;
            }else{
                throw new RuntimeException("标题头校验失败");
            }
        }
        list.add(data);
        //如果list中长度已经大于或等于BATCH_COUNT时进行存库操作
        if(list.size()>=BATCH_COUNT){
            //存库
            saveData(list,tableName,total);
            //清空list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        //读取结束后处理剩余数据
        if(list.size()>0){
            //存库
            saveData(list,tableName,total);
            //清空list
            list.clear();
        }
    }

    /**
     * 获取表头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        //只允许有一个大标题或者无标题
        if(headMap.size()>1){
            //校验title
            Boolean b = checkTile((HashMap<Integer, String>) headMap, tableName);
            if(b){
                total = headMap.size();
            }else{
                throw new RuntimeException("标题头校验失败");
            }
        }
    }

    /**
     * 加上存储数据库，达到{BATCH_COUNT}后会触发这个方法
     */
    public void saveData(List<HashMap<Integer,String>> maps,String tableName,Integer total){
        //保存的结果
        List<LinkedHashMap<String,List<String>>> rs = new ArrayList<LinkedHashMap<String, List<String>>>();

        //对数据进行解析处理
        for (int i = 0,num = maps.size();i<num;i++){

            HashMap<Integer,String> longMap = maps.get(i);

            //计数，什么是top更新
            int count = 0;

            //初始化top
            String keys = "top";

            //存放结果集的结果集
            LinkedHashMap<String,List<String>> listMap = new LinkedHashMap<>();
            //存放结果集
            List<String> strings = new ArrayList<String>();

            //参数循环
            for (int j = 0;j<total;j++){

                //为null则调过
                if(!longMap.containsKey(j)){
                    strings.add(null);
                    count = count+1;
                    //判断是否是最后一条，如果是最后一条为null则也添加
                    //对count对10取余，应该是10字段为一组
                    if(count%10==0){
                        listMap.put(keys+""+count,strings);
                        strings = new ArrayList<>();
                        continue;
                    }else if(total==(j+1)){
                        //因为count%10的时候基本也就是total-count<10，也就是不够10的时候
                        listMap.put(keys+""+decadeTop(count),strings);
                        strings = new ArrayList<>();
                    }
                    continue;
                }
                //获取参数值longMap.get(j)
                //修改为longMap.get(j)+j，给每个数据加一个标识符，为了精确查询
                String paramValue = (longMap.get(j)==null?"":longMap.get(j))+"_"+ StringUtil.paraeString4(j);
                //添加结果集
                strings.add(paramValue);
                //count自增
                count = count+1;
                //对count对10取余，应该是10字段为一组
                if(count%10==0){
                    listMap.put(keys+""+count,strings);
                    strings = new ArrayList<>();
                    continue;
                }else if(total==(j+1)){
                    //因为count%10的时候基本也就是total-count<10，也就是不够10的时候
                    listMap.put(keys+""+decadeTop(count),strings);
                    strings = new ArrayList<>();
                }

            }
            rs.add(listMap);

        }
        //保存数据

        BaseAnalysis baseAnalysis = new BaseAnalysis();
        baseAnalysis.setMaps(rs);
        baseAnalysis.setTableName(tableName);
        //保存数据库
        saveDataToDb(baseAnalysis);

    }

    /**
     * 保存数据库操作
     * @param baseAnalysis
     */
    protected abstract void saveDataToDb(BaseAnalysis baseAnalysis);

    /**
     * 生成top
     * @param count
     * @return
     */
    private String decadeTop(int count){
        return ((count/10)+1)+"0";
    }

    /**
     * 校验表头数据
     * @param data 表头
     * @param tableName 数据库表名
     * @return
     */
    public abstract Boolean checkTile(HashMap<Integer, String> data, String tableName);

}
