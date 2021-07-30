package com.summit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.summit.annotation.OperationLogDetail;
import com.summit.annotation.UserOperate;
import com.summit.dao.repository.UserInfoDao;
import com.summit.dao.entity.UserModel;
import com.summit.model.enums.OperationType;
import com.summit.model.enums.OperationUnit;
import com.summit.service.UserService;
import com.summit.util.ExcelHelper;
import com.summit.util.page.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:43
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    private String headName="标签(必填),电话(必填)";
    @Value("${fileUploadPath.path}")
    private String rootPath;


    @Override
    @UserOperate
    public void insertUserInfo(UserModel userInfo) {
        LocalDateTime date = LocalDateTime.now();
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setUserName(IdWorker.getIdStr());
        userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    public UserModel findUserByUserName(String username) {
        return null;
    }

    @Override
    public Page<UserModel> findUsersByPages(String username, String name, String sex, Date start, Date end, Integer pageIndex,Integer pageSize) {
        Integer page=(pageIndex-1)*pageSize;
        /*int toatl = userInfoDao.selectCount(Wrappers.<UserModel>lambdaQuery()
                .eq(UserModel::getUserName, username)
                .eq(UserModel::getName, name)
                .eq(UserModel::getSex, sex));*/

        int toatl=userInfoDao.findUsersCount(username,name,sex,start,end);
        List<UserModel> userModels=userInfoDao.findUsersByPages(username, name, sex, start, end, String.valueOf(page),String.valueOf(pageSize));
        Page<UserModel> pageVo=new Page<>();
        pageVo.setRecords(userModels);
        pageVo.setTotal(toatl);
        pageVo.setCurrentPage(pageIndex);
        pageVo.setPageSize(pageSize);
        return pageVo;
    }

    @Override
    public String fileImport(String username, String sex, MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = ExcelHelper.getWorkbook(file, inputStream);
        String message= getSheetModel(workbook.getSheetAt(0),username,sex);
        return message;
    }

    @Override
    public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
        String template = "user.xlsx";
        File file = ExcelHelper.exportExcelUtil(rootPath, template);
        ExcelHelper.commonStreamOperation(request,response,file);
    }

    @Override
    public void export(UserModel user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserModel> users = userInfoDao.selectList(new QueryWrapper<UserModel>().eq("acc_ctrl_pro_id", user.getEmail()));
        String xmlPath=rootPath + File.separator + "xxx.xml";
        String xlsPath=rootPath + File.separator + "xxx.xls";
        List<List<?>> dtList = new ArrayList<>();
        dtList.add(users);
        List<String> nodeId = new ArrayList<>();
        nodeId.add("user");
        ExcelHelper.export(request,response,xmlPath,xlsPath,dtList,nodeId,rootPath);
    }

    @Override
    public List<UserModel> findUsers() {
        List<UserModel> users = new ArrayList<>();
        UserModel userModel = new UserModel();
        userModel.setEmail("qqqqqq");
        users.add(userModel);
        return users;
    }

    private String getSheetModel(Sheet sheet, String username, String sex) throws Exception {
        String message="";
        List<UserModel> users = new ArrayList<>();
        List<String> headList = Arrays.asList(headName);
        int lastRowNum = sheet.getLastRowNum();
        for(int i =0; i <=lastRowNum; i++){
            Row row = sheet.getRow(i);
            if (row!=null){
                if (i==0){
                    int lastCellNum = row.getLastCellNum();
                    if (lastCellNum != headList.size()){
                        throw new Exception("传入表格列名不正确！");
                    }else {
                        for (int j =0; j< lastCellNum; j++){
                            Cell cell = row.getCell(j);
                            String cellValue = cell.getStringCellValue();
                            if (!headList.get(j).equals(cellValue)){
                                throw new Exception("传入表格列名不正确！");
                            }
                        }
                    }
                }else {
                    String label = ExcelHelper.getCellValue(row.getCell(0));
                    String phone = ExcelHelper.getCellValue(row.getCell(1));
                    String oneMsaage="第"+(i+1)+"行数据";
                    boolean flag=false;
                    if (StringUtils.isBlank(label)){
                        flag=true;
                        oneMsaage+="标签为空";
                    }else if (StringUtils.isBlank(label)){
                        flag=true;
                        oneMsaage+="电话为空";
                    }
                    if (flag){
                        message+=oneMsaage+",";
                    }else {
                        UserModel user = new UserModel();
                        user.setUserName(label);
                        user.setEmail(phone);
                        users.add(user);
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(message)){
            message= message.substring(0,message.length()-1)+"。";
        }else {
            if (!ObjectUtils.isEmpty(users)){
                userInfoDao.insertBach(users);
            }
        }
        return message;
    }
}
