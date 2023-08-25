package string;

import java.util.Scanner;

/**
 * @Auther: imi
 * @Date: 2019/5/9 17:05
 * @Description: 字符串
 */
public class Solution {

    public static void main(String[] args) {


//        String a = "1";
//        String b = "11";
//        System.out.println(addBinary(a, b));
//        strStr("aaa",
//                "a");
//        String[] strs = new String[]{"dog","racecar","car"};
//        System.out.println(longestCommonPrefix(strs));
        reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
    }

    // 二进制求和
    public static String addBinary(String a, String b) {
        String result = "";
        char[] aa     = a.toCharArray();
        char[] bb     = b.toCharArray();

        int ma    = a.length() - 1;
        int mb    = b.length() - 1;
        int carry = 0, as, bs;
        while (ma >= 0 || mb >= 0) {
            as = ma >= 0 ? Character.getNumericValue(aa[ma--]) : 0;
            bs = mb >= 0 ? Character.getNumericValue(bb[mb--]) : 0;
            result = (as + bs + carry) % 2 + result;
            carry = (as + bs + carry) / 2;
        }
        if (carry > 0) result = "1" + result;
        return result;
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int index = -1;
        if (needle.trim().length() == 0) return 0;
        char[] str = haystack.trim().toCharArray();
        char[] s   = needle.trim().toCharArray();
        if (s.length > str.length || haystack.trim().length() == 0) return -1;
        boolean first = true;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == s[0]) {
                int j = 0;
                while (j < s.length && i + j < str.length && str[i + j] == s[j]) {
                    j++;
                }
                if (j == s.length && first) {
                    first = false;
                    index = i;
                }
            }
        }

        return index;
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
     * 示例 1:
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * 所有输入只包含小写字母 a-z 。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {

        String s     = "";
        int    judge = 1;
        if (strs.length == 0) {// 数组为空直接返回""
            return s;
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char s0 = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    judge = 0;
                    break;
                }
                if (s0 != strs[j].charAt(i)) {
                    break;
                } else {
                    if (j == strs.length - 1)
                        s += s0;
                }
            }
            if (judge == 0) {
                break;
            }

        }
        return s;
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * 示例 1：
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     * @return
     */
    public static void reverseString(char[] s) {
        int  i = 0;
        int  j = s.length - 1;
        char temp;
        while (i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public static void reverseString2(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r]; // l=l^r;
            s[r] ^= s[l]; // r=l^r ^r = l
            s[l] ^= s[r]; // l=l^r ^l = r
            l++;
            r--;
        }
    }

    public static int[] countStar() {
        int[]   rslt  = new int[]{};
        int[][] stars = new int[][]{};
        int[][] q     = new int[][]{};

        Scanner sc = new Scanner(System.in);
        int     n  = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                stars[i][j] = sc.nextInt();
            }
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                q[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            rslt[i] = 0;
            for (int j = 0; j < n; j++) {
                if (q[i][0] <= stars[j][0] && stars[j][0] <= q[i][2] &&
                        q[i][1] <= stars[j][1] && stars[j][1] <= q[i][3]) {
                    rslt[i]++;
                }
            }

        }
        return rslt;
    }

    /**
     * 最短路径
     */
    public static int findMinLength() {
        int     minLen = -1;
        Scanner sc     = new Scanner(System.in);
        int     n      = sc.nextInt();
        int[][] q      = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                q[i][j] = sc.nextInt();
            }
        }
        return minLen;
    }

    /**
     * 替换空格
     * <p>
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 示例 1： 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // 扩充空间，空格数量2倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        // 若是没有空格直接返回
        if (str.length() == 0) {
            return s;
        }
        // 有空格情况 定义两个指针
        int left = s.length() - 1;// 左指针：指向原始字符串最后一个位置
        s += str.toString();
        int    right = s.length() - 1;// 右指针：指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }

    /**
     * 移除元素
     */
    public void removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
    }

    /**
     * 翻转字符串里的单词
     */

// 解法二：创建新字符数组填充。时间复杂度O(n)
    public String reverseWords(String s) {
        // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end   = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }
    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end   = 1;
        int n     = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }


}
