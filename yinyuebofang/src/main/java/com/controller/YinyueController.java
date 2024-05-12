
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 音乐
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yinyue")
public class YinyueController {
    private static final Logger logger = LoggerFactory.getLogger(YinyueController.class);

    private static final String TABLE_NAME = "yinyue";

    @Autowired
    private YinyueService yinyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private TongzhiService tongzhiService;//通知
    @Autowired
    private YinyueCollectionService yinyueCollectionService;//音乐收藏
    @Autowired
    private YinyueLiuyanService yinyueLiuyanService;//音乐评论
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("yinyueDeleteStart",1);params.put("yinyueDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yinyueService.queryPage(params);

        //字典表数据转换
        List<YinyueView> list =(List<YinyueView>)page.getList();
        for(YinyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YinyueEntity yinyue = yinyueService.selectById(id);
        if(yinyue !=null){
            //entity转view
            YinyueView view = new YinyueView();
            BeanUtils.copyProperties( yinyue , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YinyueEntity yinyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yinyue:{}",this.getClass().getName(),yinyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YinyueEntity> queryWrapper = new EntityWrapper<YinyueEntity>()
            .eq("yinyue_name", yinyue.getYinyueName())
            .eq("zan_number", yinyue.getZanNumber())
            .eq("cai_number", yinyue.getCaiNumber())
            .eq("yinyue_guanjianzi", yinyue.getYinyueGuanjianzi())
            .eq("yinyue_music", yinyue.getYinyueMusic())
            .eq("yinyue_types", yinyue.getYinyueTypes())
            .eq("yinyue_erji_types", yinyue.getYinyueErjiTypes())
            .eq("shangxia_types", yinyue.getShangxiaTypes())
            .eq("yinyue_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinyueEntity yinyueEntity = yinyueService.selectOne(queryWrapper);
        if(yinyueEntity==null){
            yinyue.setZanNumber(1);
            yinyue.setCaiNumber(1);
            yinyue.setShangxiaTypes(1);
            yinyue.setYinyueDelete(1);
            yinyue.setInsertTime(new Date());
            yinyue.setCreateTime(new Date());
            yinyueService.insert(yinyue);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",yinyue.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YinyueEntity yinyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yinyue:{}",this.getClass().getName(),yinyue.toString());
        YinyueEntity oldYinyueEntity = yinyueService.selectById(yinyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yinyue.getYinyuePhoto()) || "null".equals(yinyue.getYinyuePhoto())){
                yinyue.setYinyuePhoto(null);
        }
        if("".equals(yinyue.getYinyueMusic()) || "null".equals(yinyue.getYinyueMusic())){
                yinyue.setYinyueMusic(null);
        }
        if("".equals(yinyue.getYinyueContent()) || "null".equals(yinyue.getYinyueContent())){
                yinyue.setYinyueContent(null);
        }

            yinyueService.updateById(yinyue);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(yinyue, oldYinyueEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YinyueEntity> oldYinyueList =yinyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YinyueEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YinyueEntity yinyueEntity = new YinyueEntity();
            yinyueEntity.setId(id);
            yinyueEntity.setYinyueDelete(2);
            list.add(yinyueEntity);
        }
        if(list != null && list.size() >0){
            yinyueService.updateBatchById(list);
        }

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldYinyueList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YinyueEntity> yinyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YinyueEntity yinyueEntity = new YinyueEntity();
//                            yinyueEntity.setYinyueName(data.get(0));                    //音乐名称 要改的
//                            yinyueEntity.setYinyueUuidNumber(data.get(0));                    //音乐编号 要改的
//                            yinyueEntity.setYinyuePhoto("");//详情和图片
//                            yinyueEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            yinyueEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            yinyueEntity.setYinyueGuanjianzi(data.get(0));                    //关键字 要改的
//                            yinyueEntity.setYinyueMusic(data.get(0));                    //音乐 要改的
//                            yinyueEntity.setYinyueTypes(Integer.valueOf(data.get(0)));   //音乐类型 要改的
//                            yinyueEntity.setYinyueErjiTypes(Integer.valueOf(data.get(0)));   //二级类型 要改的
//                            yinyueEntity.setYinyueContent("");//详情和图片
//                            yinyueEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            yinyueEntity.setYinyueDelete(1);//逻辑删除字段
//                            yinyueEntity.setInsertTime(date);//时间
//                            yinyueEntity.setCreateTime(date);//时间
                            yinyueList.add(yinyueEntity);


                            //把要查询是否重复的字段放入map中
                                //音乐编号
                                if(seachFields.containsKey("yinyueUuidNumber")){
                                    List<String> yinyueUuidNumber = seachFields.get("yinyueUuidNumber");
                                    yinyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yinyueUuidNumber = new ArrayList<>();
                                    yinyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yinyueUuidNumber",yinyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //音乐编号
                        List<YinyueEntity> yinyueEntities_yinyueUuidNumber = yinyueService.selectList(new EntityWrapper<YinyueEntity>().in("yinyue_uuid_number", seachFields.get("yinyueUuidNumber")).eq("yinyue_delete", 1));
                        if(yinyueEntities_yinyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YinyueEntity s:yinyueEntities_yinyueUuidNumber){
                                repeatFields.add(s.getYinyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [音乐编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yinyueService.insertBatch(yinyueList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",yinyueList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<YinyueView> returnYinyueViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("yinyueYesnoTypes",2);
        PageUtils pageUtils = yinyueCollectionService.queryPage(params1);
        List<YinyueCollectionView> collectionViewsList =(List<YinyueCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(YinyueCollectionView collectionView:collectionViewsList){
            Integer yinyueTypes = collectionView.getYinyueTypes();
            if(typeMap.containsKey(yinyueTypes)){
                typeMap.put(yinyueTypes,typeMap.get(yinyueTypes)+1);
            }else{
                typeMap.put(yinyueTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("yinyueTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("yinyueYesnoTypes",2);
            PageUtils pageUtils1 = yinyueService.queryPage(params2);
            List<YinyueView> yinyueViewList =(List<YinyueView>)pageUtils1.getList();
            returnYinyueViewList.addAll(yinyueViewList);
            if(returnYinyueViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("yinyueYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = yinyueService.queryPage(params);
        if(returnYinyueViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnYinyueViewList.size();//要添加的数量
            List<YinyueView> yinyueViewList =(List<YinyueView>)page.getList();
            for(YinyueView yinyueView:yinyueViewList){
                Boolean addFlag = true;
                for(YinyueView returnYinyueView:returnYinyueViewList){
                    if(returnYinyueView.getId().intValue() ==yinyueView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnYinyueViewList.add(yinyueView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnYinyueViewList = returnYinyueViewList.subList(0, limit);
        }

        for(YinyueView c:returnYinyueViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnYinyueViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yinyueService.queryPage(params);

        //字典表数据转换
        List<YinyueView> list =(List<YinyueView>)page.getList();
        for(YinyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YinyueEntity yinyue = yinyueService.selectById(id);
            if(yinyue !=null){


                //entity转view
                YinyueView view = new YinyueView();
                BeanUtils.copyProperties( yinyue , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YinyueEntity yinyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yinyue:{}",this.getClass().getName(),yinyue.toString());
        Wrapper<YinyueEntity> queryWrapper = new EntityWrapper<YinyueEntity>()
            .eq("yinyue_name", yinyue.getYinyueName())
            .eq("yinyue_uuid_number", yinyue.getYinyueUuidNumber())
            .eq("zan_number", yinyue.getZanNumber())
            .eq("cai_number", yinyue.getCaiNumber())
            .eq("yinyue_guanjianzi", yinyue.getYinyueGuanjianzi())
            .eq("yinyue_music", yinyue.getYinyueMusic())
            .eq("yinyue_types", yinyue.getYinyueTypes())
            .eq("yinyue_erji_types", yinyue.getYinyueErjiTypes())
            .eq("shangxia_types", yinyue.getShangxiaTypes())
            .eq("yinyue_delete", yinyue.getYinyueDelete())
//            .notIn("yinyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinyueEntity yinyueEntity = yinyueService.selectOne(queryWrapper);
        if(yinyueEntity==null){
                yinyue.setZanNumber(1);
                yinyue.setCaiNumber(1);
            yinyue.setYinyueDelete(1);
            yinyue.setInsertTime(new Date());
            yinyue.setCreateTime(new Date());
        yinyueService.insert(yinyue);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",yinyue.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

