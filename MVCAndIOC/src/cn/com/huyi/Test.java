package cn.com.huyi;

/**
 * @title: Test
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/12 15:58
 **/

public class Test {
    @org.junit.Test
    public void test(){
        String methodRetureStr = "redirect:book";
        if (methodRetureStr.startsWith("redirect:")){
            String substring = methodRetureStr.substring("redirect:".length());
            System.out.println(substring);
        }
    }

}
