package utils.security;

import java.util.Random;

/**
 * @Description
 * @Since 2020-10-16
 */
public class PassWordGenerator {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(getPassword(25));
        }
    }

    public static String getPassword(int len){
        char charr[] = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        for(int i=0;i<len;i++){
            stringBuilder.append(charr[r.nextInt(charr.length)]);
        }
        return stringBuilder.toString();
    }

}
/**
 *
 * iFq~!lg9I6Lwa9@*jFt1
 * a7MDDOeruCkck$OV.zy4
 * qSIBanBHTnBDq$y&LV5k
 * E@3JGztLdlH?9*3JXzg%
 * DgCD1CgApWbO5M%e0.z6
 * YyJ@DfQQ6U256AN7W3%?
 * #P&Dta2divtvj6qEX9#Z
 * 069xk@!Bb9O@GVDN0j9p
 * &9Vr6XbpfYIwYI9eOjEz
 * x7sqcdj16r^YnqTtAR$~
 *
 *
 * ET64@!xJ#jgyUoh@JxSlg30Z^
 * r%O#vH5UI@*46FX14lk!00RiR
 * Zp2lN^7ySq66ZwYg7K54S0D88
 * 30T^0OhuXyJ7^ivX42zMjK01R
 * ?ac8.JS@ElN^16tYPf5mi5Req
 * &KTLhX4AfE1tKFxDH2l0BI2nP
 * fMeU39cWXYExXQ7DkUj0qPRkE
 * pgXV5S!25~IW3U993?QOm5$A%
 * bEHkr2s4H~Yq*~YyFD@qztkG4
 * yG^!VY14XJ74G.0wt.3*rru5L
 * QiQv!!DI^94Abu!sqX#qosrWf
 * wWgd6SVzqk06V5jdLFU5218%F
 * M8JzW0Q2Ns.yHZ1d&eJ~E5s!M
 * iiKnSs~gUkGY^bl1oeEQE4&fa
 * 51@66596p9UQ1mP26l7W5~*u1
 * sn44sZYMJl3IUpF4FkaR7*!~6
 * a$EB3XyT!^%eNVd.R0ohPNXq#
 * E8708Ka3v8i8o?nP6z3h4P8u1
 * ZbfP16HCBB6xm7%r5~$k2On&%
 * zt?ZBA62EnT$i0TY440f*%2kW
 * 2I7K6ar5vIV7eJdK~0qb$lAWV
 * sQyKhdA6Jr*#4h#f!LgjYSPyS
 * aG5msD5k8ieFzL94?Wdd4n?#O
 * 23OL0@FT&OF3.uL643&k4R2d^
 * J4b2L751bSx38UdKdb1mXtNhu
 * IWeTFlO%T.F6Y8S$Fi&rZ0X1o
 * j8jY25xtAnB4TypNeZqlwmL9Z
 * #0EqZ55QqHxJM*E2Om&Eab1l2
 * VsAR88e1rGCL36$z9U7~*nh~Q
 * #b^Co2To5Rtj31W^UifNPz711
 * vRQf#~M0m@P1YHa?1hGO7Nq!^
 * dsGjZ@xknYJ9YCN8Y200Vp&b4
 * ILwJa2yYsDS7XMxJo4bBlLjse
 * !K3ZKKnqR3LjnvvuEOf6sE@qn
 * Xr7hb7Pf8p?ZDv2N.n@#Mi75Z
 * &&Yo36b67nn70iWtWjfy~uKvD
 * fYG?dZJVv@u4AF~DWViHagv2Z
 * baic%JV*9iwXqENOiHU&2bRp6
 * 1JJTEJ7M1db!4NAALof??~631
 * 9T~e&n874Tihl&Q4v^~Nf84Ak
 * *0do7sns&3Fs28&AvIN&urR6U
 * AeG^1QNnb5@pya5G45tF6KqKY
 * DlTZzE#@y%i?hcy2.OQ2S*D1b
 * $9u!P36FXRH6~!T1Tz6EJxdYi
 * @Xt5tXn8!030Po6kRJ8F1X8xd
 * 34a$j31Dg?vjzxPaR3mru1m*9
 * 3NvzWS3*UY6&Y??PV11%DX2hJ
 * bf9&sP5SgiNvk39a4Ptx@6wh%
 * gl3QDc3a0Ivjocu#Yh3EVfs@*
 * #tESsHX!2$E3Z9FDZ5i1j9xR%
 */