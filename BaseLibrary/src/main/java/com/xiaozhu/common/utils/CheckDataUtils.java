package com.xiaozhu.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @说明 数据校验
 * @作者 liuyi
 * @时间 2018/4/10 15:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class CheckDataUtils {
    /**
     * 校验输入
     *
     * @param reg    验证的表达式
     * @param string 校验数据
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    private static boolean startCheck(String reg, String string) {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }

    /**
     * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头, 正整数不能以0开头
     *
     * @param nr 数字
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkNr(String nr) {
        String reg = "^(-?)[1-9]+\\d*|0";
        return startCheck(reg, nr);
    }

    /**
     * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
     *
     * @param cellPhoneNr 手机号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkCellPhone(String cellPhoneNr) {
        String reg = "^[1][\\d]{10}";
        return startCheck(reg, cellPhoneNr);
    }

    /**
     * 座机校验
     *
     * @param telPhone 座机号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkTelPhone(String telPhone) {
        String reg = "^(\\d{3,4}\\-?)?\\d{7,8}$";
        return startCheck(reg, telPhone);
    }

    /**
     * 判断手机号是否正确 13.15.18.17开头
     *
     * @param phone 需要校验的手机号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern1 = Pattern.compile("^13\\d{9}||15[8,9]\\d{8}$");
        Pattern pattern2 = Pattern.compile("^15\\d{9}||15[8,9]\\d{8}$");
        Pattern pattern3 = Pattern.compile("^18\\d{9}||15[8,9]\\d{8}$");
        Pattern pattern4 = Pattern.compile("^17\\d{9}||15[8,9]\\d{8}$");
        Matcher matcher1 = pattern1.matcher(phone);
        Matcher matcher2 = pattern2.matcher(phone);
        Matcher matcher3 = pattern3.matcher(phone);
        Matcher matcher4 = pattern4.matcher(phone);

        if (matcher1.matches() || matcher2.matches() || matcher3.matches() || matcher4.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 检验空白符
     *
     * @param line 需要校验的数据
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkWhiteLine(String line) {
        String regex = "(\\s|\\t|\\r)+";
        return startCheck(regex, line);
    }

    /**
     * 检查EMAIL地址 用户名和网站名称必须>=1位字符
     * 地址结尾必须是以com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn结尾
     *
     * @param email 邮箱
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkEmailWithSuffix(String email) {
        String regex = "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";
        return startCheck(regex, email);
    }

    /**
     * 检查EMAIL地址 用户名和网站名称必须>=1位字符 地址结尾必须是2位以上，如：cn,test,com,info
     *
     * @param email 邮箱
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+\\@\\w+\\.\\w{2,}";
        return startCheck(regex, email);
    }

    /**
     * 检查邮政编码(中国),6位，第一位必须是非0开头，其他5位数字为0-9
     *
     * @param postCode 邮编
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPostcode(String postCode) {
        String regex = "^[1-9]\\d{5}";
        return startCheck(regex, postCode);
    }

    /**
     * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
     *
     * @param username 用户名
     * @param max      最大长度
     * @param min      最小长度
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkUsername(String username, int min, int max) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
        return startCheck(regex, username);
    }

    /**
     * 校验密码 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 用户名有最小长度和最大长度限制，比如密码必须是4-20位
     *
     * @param password 密码
     * @param min      最小长度
     * @param max      最大长度
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPassword(String password, int min, int max) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
        return startCheck(regex, password);
    }

    /**
     * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 有最小位数限制的用户名，比如：用户名最少为4位字符
     *
     * @param username 用户名
     * @param min      最小长度
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkUsername(String username, int min) {
        String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";
        return startCheck(regex, username);
    }

    /**
     * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字 最少一位字符，最大字符位数无限制，不能以"_"结尾
     *
     * @param username 用户名
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkUsername(String username) {
        String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
        return startCheck(regex, username);
    }

    /**
     * 查看IP地址是否合法
     *
     * @param ipAddress IP地址
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkIP(String ipAddress) {
        String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";
        return startCheck(regex, ipAddress);
    }

    /**
     * 验证国内电话号码 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位
     *
     * @param phoneNr 验证国内电话号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPhoneNr(String phoneNr) {
        String regex = "^[0]\\d{2,3}\\-\\d{7,8}";
        return startCheck(regex, phoneNr);
    }

    /**
     * 验证国内电话号码 格式：6767676, 号码位数必须是7-8位,头一位不能是"0"
     *
     * @param phoneNr 验证国内电话号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPhoneNrWithoutCode(String phoneNr) {
        String reg = "^[1-9]\\d{6,7}";
        return startCheck(reg, phoneNr);
    }

    /**
     * 验证国内电话号码 格式：0106767676，共11位或者12位，必须是0开头
     *
     * @param phoneNr 验证国内电话号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkPhoneNrWithoutLine(String phoneNr) {
        String reg = "^[0]\\d{10,11}";
        return startCheck(reg, phoneNr);
    }

    /**
     * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
     *
     * @param idNr 身份证号码
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkIdCard(String idNr) {
        String reg = "^[1-9](\\d{14}|\\d{17})";
        return startCheck(reg, idNr);
    }

    /**
     * 网址验证<br>
     * 符合类型：<br>
     * http://www.test.com<br>
     * http://163.com
     *
     * @param url 网址
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkHttp(String url) {
        String reg = "/^([hH][tT]{2}[pP]:\\/\\/|[hH][tT]{2}[pP][sS]:\\/\\/)(([A-Za-z0-9-~]+)\\.)+([A-Za-z0-9-~\\/])+$/";
        return startCheck(reg, url);
    }

    /**
     * 网址验证<br>
     * 符合类型：<br>
     * https://www.test.com<br>
     * https://163.com
     *
     * @param url 网址
     * @return {@code true}: 验证成功<br>{@code false}: 验证失败
     */
    public static boolean checkHttps(String url) {
        String reg = "https://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
        return startCheck(reg, url);
    }
}
