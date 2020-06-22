/**
 * @author pengzh
 * @date 2020-02-27
 */

/**
 * @author     ：pengzh
 * @date       ：Created in 2020/2/27 17:00
 * @description：
 * @modified By：
 */
class Base {
    private void amethod(int iBase) {
        System.out.println("Base.amethod");
    }
}
class Over extends Base {
    public static void main(String args[]) {
        Over o = new Over();
        int iBase = 0 ;
        o.amethod(iBase) ;
    }
    public void amethod(int iOver) {
        System.out.println("Over.amethod");
    }
}