package cn.stylefeng.star.modular.bussines.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.star.core.common.annotion.Permission;
import cn.stylefeng.star.core.common.constant.Const;
import cn.stylefeng.star.core.common.page.LayuiPageFactory;
import cn.stylefeng.star.modular.bussines.entity.TAnnex;
import cn.stylefeng.star.modular.bussines.entity.TPart;
import cn.stylefeng.star.modular.bussines.entity.TWarehous;
import cn.stylefeng.star.modular.bussines.service.TWarehousService;
import cn.stylefeng.star.modular.bussines.warpper.WarehousWarpper;
import cn.stylefeng.star.modular.util.DateUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: zhangty
 * @Date: 2019/3/13 09:10
 * @Description:项目申报
 * @Version:1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/warehous")
public class TWarehousController  extends BaseController {

    private static String PREFIX = "/modular/bussines/warehous/";

    @Autowired
    private TWarehousService tWarehousService;


    @RequestMapping("")
    public String list(){
        return PREFIX+"list.html";
    }


    /**
     *
     *  warehous
     * @author zhangty
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String companyName,
                       @RequestParam(required = false) String companyOrgNo,
                       @RequestParam(required = false) String industryType,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String productPerson,
                       @RequestParam(required = false) String crtTime) {
        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();
        List<Map<String,Object>> result = tWarehousService.getWarehous(page,companyName,companyOrgNo,
                industryType,productName,productPerson,crtTime);
        log.info("返回的结果:{}",result);
        page.setRecords(new WarehousWarpper(result).wrap());
        return LayuiPageFactory.createPageInfo(page);
    }


    /**
     * 审核
     * @param id
     * @return
     */
    @RequestMapping(value="reivew")
    @ResponseBody
    public ResponseData review(@RequestParam Integer id){
        TWarehous model = new TWarehous();
        model.setId(id);
        model.setWarehousStatus(1);
        tWarehousService.editWarehous(model);
        return SUCCESS_TIP;
    }


    /**
     * 退回
     * @param id
     * @return
     */
    @RequestMapping(value="refund")
    @ResponseBody
    public ResponseData refund(@RequestParam Integer id){
        TWarehous model = new TWarehous();
        model.setId(id);
        model.setWarehousStatus(2);
        tWarehousService.editWarehous(model);
        return SUCCESS_TIP;
    }


    /**
     * 下载
     * @param companyName
     * @param companyOrgNo
     * @param industryType
     * @param productName
     * @param productPerson
     * @param crtTime
     * @return
     */
    @RequestMapping(value="downExcel")
    @ResponseBody
    public List<TWarehous> getDownExcel(@RequestParam(required = false) String companyName,
                                        @RequestParam(required = false) String companyOrgNo,
                                        @RequestParam(required = false) String industryType,
                                        @RequestParam(required = false) String productName,
                                        @RequestParam(required = false) String productPerson,
                                        @RequestParam(required = false) String crtTime) throws  Exception{
        TWarehous model = new TWarehous();
        model.setCompanyName(companyName);
        model.setCompanyOrgNo(companyOrgNo);
        model.setIndustryType(industryType);
        model.setProductName(productName);
        model.setProductPerson(productPerson);
        model.setCrtTime(DateUtils.getNowDate(crtTime));
        List<TWarehous> list = tWarehousService.getDownExcel(model);
        log.info("结果数量：{}",list.size());
        return list;
    }



    /**
     * 跳转到详情
     * @author zhangty
     */
    @RequestMapping("/warehous_detail")
    public String newDetail(@RequestParam Integer id) {
        return PREFIX + "warehous_detail.html";
    }

    /**
     * 详情
     * @author zhangty
     * @Date 2018/12/23 4:57 PM
     */
    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public Object detail(@PathVariable("id") Integer id) {
        TWarehous dto = tWarehousService.findById(id);
        return dto;
    }

    @RequestMapping(value="/annexImg/{itemNo}")
    @ResponseBody
    public List<TAnnex> getAnnexImg(@PathVariable("itemNo") String itemNo){
        List<TAnnex> lists = tWarehousService.getUploadName(itemNo);
        log.info("查询所有附件总数:{}",lists.size());
        return lists;
    }

    @RequestMapping(value="part/{itemNo}")
    @ResponseBody
    public List<TPart> getPartList(@PathVariable("itemNo") String itemNo){
        List<TPart> list = tWarehousService.getPartList(itemNo);
        return list;
    }





    @RequestMapping("/down")
    public void down(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.info("开始执行下载:{}");
        //服务器地址
        String rootPath = "";
        rootPath = "C:\\Users\\zhangty\\yf项目\\star\\target\\classes\\static\\upload\\";
        // rootPath ="C:\\Users\\zhangty\\yf项目\\guns\\src\\main\\resources\\static\\upload\\";
        String itemNo = request.getParameter("itemNo");
        log.info("获取的编号:{}",itemNo);
        List files = new ArrayList();
        List<TAnnex> lists = tWarehousService.getUploadName(itemNo);
        if(lists==null){

        }
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).getAnnexName()!=null){
                File file = new File(rootPath+lists.get(i).getAnnexName());
                log.info("选择下载的文件:{}",file.getName());
                files.add(file);
            }
        }
        downLoadFiles(files, response);
        log.info("成功");
    }

    public static HttpServletResponse downLoadFiles(List<File> files, HttpServletResponse response) throws Exception {

        try {
            // List<File> 作为参数传进来，就是把多个文件的路径放到一个list里面
            // 创建一个临时压缩文件

            // 临时文件可以放在CDEF盘中，但不建议这么做，因为需要先设置磁盘的访问权限，最好是放在服务器上，方法最后有删除临时文件的步骤
            String zipFilename = "D:/tempFile.zip";
            File file = new File(zipFilename);
            file.createNewFile();
            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();
            // response.getWriter()
            // 创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 把接受的全部文件打成压缩包
     * @param
     */
    public static void zipFile(List files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        if (file.exists() == false) {
            System.out.println("待压缩的文件目录：" + file + "不存在.");
        } else {
            try {
                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();

                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");

                // 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }




}
