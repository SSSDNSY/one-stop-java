package utils.security;

import java.util.Random;

/**
 * @Description
 * @Since 2020-10-16
 */
public class PassWordGenerator {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(getPassword(26));
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
 *
 * 2021年3月5日 16:09:58
 *
 * Ps9E8t5Cwo0zJEX&aa%dC8Mu*.
 * eO7x?S!Mho5iNe@!o^l&tpU6Y!
 * H9%gDHMz6w9mgVPkMUKA65Rzuf
 * f65QMYnn6L731&Y3r3Hh5#uYIR
 * CGxGMMEdc4YjGg637XaDIX542F
 * B1%NDoU5G0*@6QtRbxpg5lj65~
 * fxOQRW0%GsuCB#@9bu85$ypnO5
 * U&AMXJc&wh4S516l541woY0hMO
 * OSEjWnkrGJD0x?Lz$nTy3CAUs&
 * ~^5BAnv38M0CeWL56DY68w^3QP
 * a?jV72RCo7R5!I#5e9g27lb3r8
 * &%L?8EcfrvWwo54?Rb0va$?92O
 * aK3d8bP4DeLHp008U0qS6291DY
 * !HFk$aI1jQEjKFGEJ.@rwOlY?n
 * qK128yEIF^W@NDjd!7!o4R74P*
 * Rhxq9Y4cmriw2f?c*Fjn239.6Z
 * 5aI?fRWn!6e~7z727TB1r~w6kE
 * ddmi&1yrW4^*%1mgfmg9.wviC%
 * q2SW&!vo&y6w%*g@ONX9rR3G&b
 * R31U74iHNTCv.Ict8zi&5J.P02
 * u@UH#TXfdS%^ndf.l2gx7Fn29f
 * %n*n9i^9Q.FR2R5lb~MFhg1vg7
 * M~MCLiW5C8%oJ1MQItQD369oeU
 * 3*mV14S?N?S9Qy3s7UjB!PG6Ao
 * ##j2TOfGT8nYn*Mu%swFN1VQ3l
 * ED20vhh?Ci8jGQ611X0Az^#g72
 * 1q8N@*%65&y.BQk95nwi$7I6Yf
 * 050RUAtv1ITwZrAuC%2?m1n*8b
 * S7W0WZEa9v9hV2ZT%M@I491d&e
 * n*~2KO5NH7matl9Idx1H8dX#yF
 * IclL~ye59n@Uj$!Qg61~DoEGFg
 * SPO##GBI%s~H89Nu2?3fv%~U3x
 * ~M1yajW.VpF4q7KkUAkg0Xbb30
 * I%@b44TYHZIaR2H!@LGQS35Qs6
 * N1*UCNQI3e4?0UE#tLJd#%BY85
 * l7B#Hx0$X2m7f3F493ab98C57S
 * T3foA8v5gKLgJFEGg&6KJvAe42
 * l8ye3M?4uVpI5cduB8QRUVyO2K
 * BcZprHfAmM2CSce.*TF0Z?5Glj
 * 1&7LN@41Y&i?i.b1X1??U2D~g0
 * H3FFHDu5O1TNVNlDgcx1wdhyej
 * 34WvNqHXRf2WV.Uf3?z62QWDu?
 * ~RL08$*S44XS69Tv48No0wDguN
 * A9J1%avgEq976lN0YeBT?mOxtg
 * dGm1NHwXOd5BvH6i11f!Vzd@5i
 * 61v?4JlCAf9*OaIXZ0L3I$75z^
 * E*!~Dn&N15.lJH.L7v~otye3K6
 * ?Q%g^JA3eou~6oyq3G!j3GZhuS
 * 43hf?tbcosmQZY.BMJa9*S0KfN
 * ~2qO@kNv7e$d.4*@H0#&6VD08k
 *
 */