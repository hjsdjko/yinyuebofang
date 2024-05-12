
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
 * 音乐评论
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yinyueLiuyan")
public class YinyueLiuyanController {
    private static final Logger logger = LoggerFactory.getLogger(YinyueLiuyanController.class);

    private static final String TABLE_NAME = "yinyueLiuyan";

    @Autowired
    private YinyueLiuyanService yinyueLiuyanService;


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
    private YinyueService yinyueService;//音乐
    @Autowired
    private YinyueCollectionService yinyueCollectionService;//音乐收藏
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
        CommonUtil.checkMap(params);
        PageUtils page = yinyueLiuyanService.queryPage(params);

        //字典表数据转换
        List<YinyueLiuyanView> list =(List<YinyueLiuyanView>)page.getList();
        for(YinyueLiuyanView c:list){
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
        YinyueLiuyanEntity yinyueLiuyan = yinyueLiuyanService.selectById(id);
        if(yinyueLiuyan !=null){
            //entity转view
            YinyueLiuyanView view = new YinyueLiuyanView();
            BeanUtils.copyProperties( yinyueLiuyan , view );//把实体数据重构到view中
            //级联表 音乐
            //级联表
            YinyueEntity yinyue = yinyueService.selectById(yinyueLiuyan.getYinyueId());
            if(yinyue != null){
            BeanUtils.copyProperties( yinyue , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYinyueId(yinyue.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yinyueLiuyan.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody YinyueLiuyanEntity yinyueLiuyan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yinyueLiuyan:{}",this.getClass().getName(),yinyueLiuyan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yinyueLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        yinyueLiuyan.setCreateTime(new Date());
        yinyueLiuyan.setInsertTime(new Date());
        yinyueLiuyanService.insert(yinyueLiuyan);

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",yinyueLiuyan.toString());
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YinyueLiuyanEntity yinyueLiuyan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yinyueLiuyan:{}",this.getClass().getName(),yinyueLiuyan.toString());
        YinyueLiuyanEntity oldYinyueLiuyanEntity = yinyueLiuyanService.selectById(yinyueLiuyan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yinyueLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(yinyueLiuyan.getYinyueLiuyanText()) || "null".equals(yinyueLiuyan.getYinyueLiuyanText())){
                yinyueLiuyan.setYinyueLiuyanText(null);
        }
        if("".equals(yinyueLiuyan.getReplyText()) || "null".equals(yinyueLiuyan.getReplyText())){
                yinyueLiuyan.setReplyText(null);
        }
        yinyueLiuyan.setUpdateTime(new Date());

            yinyueLiuyanService.updateById(yinyueLiuyan);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(yinyueLiuyan, oldYinyueLiuyanEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YinyueLiuyanEntity> oldYinyueLiuyanList =yinyueLiuyanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yinyueLiuyanService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldYinyueLiuyanList.toString());
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
            List<YinyueLiuyanEntity> yinyueLiuyanList = new ArrayList<>();//上传的东西
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
                            YinyueLiuyanEntity yinyueLiuyanEntity = new YinyueLiuyanEntity();
//                            yinyueLiuyanEntity.setYinyueId(Integer.valueOf(data.get(0)));   //音乐 要改的
//                            yinyueLiuyanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yinyueLiuyanEntity.setYinyueLiuyanText(data.get(0));                    //评论内容 要改的
//                            yinyueLiuyanEntity.setInsertTime(date);//时间
//                            yinyueLiuyanEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            yinyueLiuyanEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            yinyueLiuyanEntity.setCreateTime(date);//时间
                            yinyueLiuyanList.add(yinyueLiuyanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yinyueLiuyanService.insertBatch(yinyueLiuyanList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",yinyueLiuyanList.toString());
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yinyueLiuyanService.queryPage(params);

        //字典表数据转换
        List<YinyueLiuyanView> list =(List<YinyueLiuyanView>)page.getList();
        for(YinyueLiuyanView c:list)
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
        YinyueLiuyanEntity yinyueLiuyan = yinyueLiuyanService.selectById(id);
            if(yinyueLiuyan !=null){


                //entity转view
                YinyueLiuyanView view = new YinyueLiuyanView();
                BeanUtils.copyProperties( yinyueLiuyan , view );//把实体数据重构到view中

                //级联表
                    YinyueEntity yinyue = yinyueService.selectById(yinyueLiuyan.getYinyueId());
                if(yinyue != null){
                    BeanUtils.copyProperties( yinyue , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYinyueId(yinyue.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yinyueLiuyan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody YinyueLiuyanEntity yinyueLiuyan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yinyueLiuyan:{}",this.getClass().getName(),yinyueLiuyan.toString());
        yinyueLiuyan.setCreateTime(new Date());
        yinyueLiuyan.setInsertTime(new Date());
        yinyueLiuyanService.insert(yinyueLiuyan);

            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"前台新增",yinyueLiuyan.toString());
            return R.ok();
        }

}

