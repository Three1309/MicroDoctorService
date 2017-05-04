package com.zhuolang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.Doctor;
import com.zhuolang.service.IDoctorService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.model.User;
import com.zhuolang.service.IUserService;


/**
 * Created by wnf on 2017/4/13.
 */
@Controller
public class UserAction extends ActionSupport{

    private static final long serialVersionUID = 1L;

    @Autowired
    IUserService userService;

    @Autowired
    IDoctorService doctorService;

    /**
     *  登录，成功找到账号，则返回用户权限值和用户基本信息
     *  @return
     *  @throws IOException
     */
    public String login() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
//      通过电话号码来登录，电话号码是唯一，且不为空

        User user = new User();

        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));

        String result;
        List<User> userList=userService.userLogin(user);
        System.out.println(userList);
        if (userList != null && userList.size() > 0) {
            result = "login_success" + userList.get(0).getType();
        } else {
            result = "login_failure";
        }
        PrintWriter out = response.getWriter();
        out.print(result);//返回登录的结果，success or failure
        out.flush();
        out.close();
        return null;
    }

    /**
     *   注册用户
     *   查找号码，看是否已经有注册，如果没有则注册为普通用户
     *   @return
     *   @throws IOException
     */
    public String addUser()throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        // 测试插入数据
        String phone = request.getParameter("phone");

        //通过phone找用户,是否已存在改用户
        if (userService.findPhone(phone)) {
            PrintWriter out = response.getWriter();
            out.print("register_failure");//该电话号码已经注册，请输入其他电话号码
            out.flush();
            out.close();
        } else {
            User user = new User();
            user.setPhone(phone);
            user.setPassword(request.getParameter("password"));
            user.setType(0);
            userService.addUser(user);

            //在这里添加失败会怎么样？
            PrintWriter out = response.getWriter();
            out.print("register_success");
            out.flush();
            out.close();
        }
        return null;
    }

    /**
     * 密码修改
     * @return
     * @throws IOException
     */
    public String updatePassword() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        boolean flag = userService.updatePassword(id, request.getParameter("oldPassword"), request.getParameter("newPassword"));
        String result;
        if (flag) {
            result = "updatePassword_success";
        } else {
            result = "updatePassword_failure";
        }
        // 测试输出json数据
        PrintWriter out = response.getWriter();
        // JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
        out.print(result);
        out.flush();
        out.close();
        return null;
    }

    /**
     * （账号）手机修改
     * @return
     * @throws IOException
     */
    public String updatePhone() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        String idStr = request.getParameter("id");
        String phoneStr = request.getParameter("phone");
        int id = Integer.parseInt(idStr);

        boolean flag = userService.updatePhone(id, phoneStr);
        String result;
        if (flag) {
            result = "updatePhone_success";
        } else {
            result = "updatePhone_failure";
        }
        // 测试输出json数据
        PrintWriter out = response.getWriter();
        // JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
        out.print(result);
        out.flush();
        out.close();
        return null;
    }


    /**
     * 用户个人信息资料展示  、医师个人资料展示
     *
     * @throws IOException
     */
    public String findByPhone() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        List<User> list = userService.findByPhone(request.getParameter("phone"));
        if (list != null && list.size() > 0) {
            DoctorDto userDto=new DoctorDto();
            userDto.setId(list.get(0).getId());
            userDto.setPhone(list.get(0).getPhone());
            userDto.setName(list.get(0).getName());
            userDto.setNickname(list.get(0).getNickname());
            userDto.setGender(list.get(0).getGender());
            userDto.setAge(list.get(0).getAge());
            userDto.setAddress(list.get(0).getAddress());
            userDto.setSignature(list.get(0).getSignature());
            userDto.setIntroduction(list.get(0).getIntroduction());
            List<Doctor> doctorList=doctorService.findDoctorByDoctorId(list.get(0).getId());
            if (doctorList != null && doctorList.size() > 0) {
                if (list.get(0).getType()==1){
                    userDto.setType(1);
                    userDto.setHospital(doctorList.get(0).getHospital());
                    userDto.setOffice(doctorList.get(0).getOffice());
                    userDto.setAmount(doctorList.get(0).getAmount());
                    userDto.setLikenum(doctorList.get(0).getLikenum());
                }
                else if(list.get(0).getType()==0 && doctorList.get(0).getAmount()==-1) {
                    userDto.setType(0);
                    userDto.setHospital(doctorList.get(0).getHospital());
                    userDto.setOffice(doctorList.get(0).getOffice());
                    userDto.setAmount(doctorList.get(0).getAmount());
                    userDto.setLikenum(0);
                }
            }
            else {
                userDto.setType(list.get(0).getType());
                userDto.setHospital("");
                userDto.setOffice("");
                userDto.setAmount(0);
                userDto.setLikenum(0);
            }
            JSONObject jsonObject=new JSONObject();
            String userJson=jsonObject.toJSONString(userDto);
            //即是获取到密码，但是（model中的toString）不展示出来，就是安卓界面里没有这个展示项，或者后期加密后获取到也没有
            PrintWriter out = response.getWriter();
            out.print(userJson);
            out.flush();
            out.close();

        }else {
            JSONObject jsonObject=new JSONObject();
            String userJson=jsonObject.toJSONString(new DoctorDto());
            //即是获取到密码，但是（model中的toString）不展示出来，就是安卓界面里没有这个展示项，或者后期加密后获取到也没有
            PrintWriter out = response.getWriter();
            out.print(userJson);
            out.flush();
            out.close();
        }

        return null;
    }

    /**
     * 普通资料修改(手机、密码跟用户类型（普通用户或医师）不允许改)
     * @return
     * @throws IOException
     */
    public String updateUser() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        User user = new User();
        //从当前登录的用户中获取id
        int id = Integer.parseInt(request.getParameter("id"));
        user.setId(id);
        user.setNickname(request.getParameter("nickname"));
        user.setName(request.getParameter("name"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        user.setGender(gender);
        user.setAddress(request.getParameter("address"));
        user.setSignature(request.getParameter("signature"));
        user.setIntroduction(request.getParameter("introduction"));
        int age = Integer.parseInt(request.getParameter("age"));
        user.setAge(age);
        int type = Integer.parseInt(request.getParameter("type"));
        PrintWriter out = response.getWriter();
        if (userService.updateUser(user)) {
            if (type==1){
                List<Doctor> doctorList=doctorService.findDoctorByDoctorId(id);
                if (doctorList != null && doctorList.size() > 0) {
                    doctorList.get(0).setId(doctorList.get(0).getId());
                    doctorList.get(0).setDoctorId(id);
                    doctorList.get(0).setHospital(request.getParameter("hospital"));
                    doctorList.get(0).setOffice(request.getParameter("office"));
                    doctorList.get(0).setAmount(doctorList.get(0).getAmount());
                    doctorList.get(0).setLikenum(doctorList.get(0).getLikenum());
                    doctorService.updateDoctor(doctorList.get(0));
                }

                out.print("update_success");
            }else {
                out.print("update_success");
            }
        }else {
            out.print("update_failure");
        }
        out.flush();
        out.close();
        return null;
    }

    /**
     * 查看医师列表（用户类型为1）
     * @return
     * @throws IOException
     */
    public String findByType() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        int type = Integer.parseInt(request.getParameter("type"));
        if (type == 1) {
            List<DoctorDto> doctorDtos = userService.findDoctorDto();
            if (doctorDtos != null && doctorDtos.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (DoctorDto a : doctorDtos) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                    jsonArray.add(jsonObj);
                }
                PrintWriter out = response.getWriter();
                out.print(jsonArray.toString());
                out.flush();
                out.close();
            }else {
                PrintWriter out = response.getWriter();
                out.print("nodata");
                out.flush();
                out.close();
            }
//        String jsonUser = JSON.toJSONString(doctorDtos, true);
//        PrintWriter out = response.getWriter();

        } else {
            List<User> userList = userService.findUserByType(type);
            if (userList != null && userList.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (User a : userList) {
                    JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                    jsonArray.add(jsonObj);
                }
//            String jsonStr = JSON.toJSONString(userList, true);
                PrintWriter out = response.getWriter();
                out.print(jsonArray.toString());
                out.flush();
                out.close();
            }else {
                PrintWriter out = response.getWriter();
                out.print("nodata");
                out.flush();
                out.close();
            }

        }
        return null;
    }

    /**
     * 查看医师列表（用户类型为1）
     * @return
     * @throws IOException
     */
    public String findByOfficeAndHospital() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        String office = request.getParameter("office");
        String hospital = request.getParameter("hospital");


        List<DoctorDto> doctorDtos = userService.findDoctorDtoByOffandHosp(office,hospital);
        if (doctorDtos != null && doctorDtos.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (DoctorDto a : doctorDtos) {
                JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                jsonArray.add(jsonObj);
            }
            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            out.flush();
            out.close();
        }else {
            PrintWriter out = response.getWriter();
            out.print("nodata");
            out.flush();
            out.close();
        }
        return null;
    }

    /**
     * 普通用户申请成为医师
     * @return
     * @throws IOException
     */
    public String registerDoctor() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        // 测试插入数据
        String phone = request.getParameter("phone");
        String hospital = request.getParameter("hospital");
        String office = request.getParameter("office");

        List<User> userList = userService.findByPhone(phone);
        PrintWriter out = response.getWriter();
        if (userList != null && userList.size() > 0) {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(userList.get(0).getId());
            doctor.setHospital(hospital);
            doctor.setOffice(office);
            doctor.setAmount(-1);//没有成为医师，则就诊量为-1
            doctorService.addDoctor(doctor);

            //在这里添加失败会怎么样？
            out.print("ask_success");
        }else {
            out.print("ask_failure");
        }
        out.flush();
        out.close();


        return null;
    }

    /**
     * 用户更新注册医师信息
     * @return
     * @throws IOException
     */
    public String updateRegisterDoctor() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        // 测试插入数据
        String phone = request.getParameter("phone");
        String hospital = request.getParameter("hospital");
        String office = request.getParameter("office");

        List<User> userList = userService.findByPhone(phone);
        PrintWriter out = response.getWriter();
        if (userList != null && userList.size() > 0) {
            if(doctorService.updateRegiDoctorInfo(userList.get(0).getId(),hospital,office)){
                out.print("ask_success");
            }else {
                out.print("ask_failure");
            }
        } else {
            out.print("ask_failure");
        }
        out.flush();
        out.close();

        return null;
    }


    /**
     * 管理员查看普通用户成为医师的表
     * @return
     * @throws IOException
     */
    public String findRegisterDoctor() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        int type = Integer.parseInt(request.getParameter("type"));
        //Doctor表中找到请求注册为医师的信息
        List<DoctorDto> doctorDtoList = doctorService.findAllnoDoctors();
        PrintWriter out = response.getWriter();
        if (doctorDtoList != null && doctorDtoList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (DoctorDto a : doctorDtoList) {
                JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                jsonArray.add(jsonObj);
            }
            out.print(jsonArray.toString());
        }else
            out.print("nodata");

        out.flush();
        out.close();
        return null;
    }

    /**
     * 管理员 通过普通用户成为医师
     * @return
     * @throws IOException
     */
    public String agreeRegisterDoctor() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        // 测试插入数据
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        PrintWriter out = response.getWriter();
        if(doctorService.updateRegiDoctor(doctorId) && userService.updateUserType(doctorId)){
            out.print("agreeask_success");
        }else {
            out.print("agreeask_failure");
        }

        out.flush();
        out.close();
        return null;
    }

    /**
     * 管理员 不通过普通用户成为医师，删除Doctor表中的申请信息
     * @return
     * @throws IOException
     */
    public String disAgreeRegisterDoctor() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        // 测试插入数据
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        PrintWriter out = response.getWriter();
        if(doctorService.deleteDoctorByDoctorId(doctorId)){
            out.print("disagreeask_success");
        }else {
            out.print("disagreeask_failure");
        }

        out.flush();
        out.close();
        return null;
    }







}















