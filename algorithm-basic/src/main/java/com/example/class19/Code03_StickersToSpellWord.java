package com.example.class19;

import java.util.HashMap;

/**
 * @Date: 2023/1/30 12:17
 * 贴纸拼词
 *
 * 题目：给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文，arr每一个字符串，代表一张贴纸，
 * 你可以把单个字符剪开使用，目的是拼出str来，返回需要至少多少张贴纸可以完成整个任务。
 * 例：str=“babac”，arr={“ba”，“c”，“”abcd}。至少需要两张贴纸“ba”和“abcd”，
 * 因为使用这两张贴纸，把每一个字符单独剪开，含有2个a，2个b，1个c。是可以拼出str的，所以返回2。
 *
 * 测试链接：https://leetcode.cn/problems/stickers-to-spell-word
 */
public class Code03_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 所有贴纸stickers，每一种贴纸都有无穷张
    // target
    // 最少张数
    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char cha : str1) {
            count[cha - 'a']++;
        }
        for (char cha : str2) {
            count[cha - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    //核心思想：将stickers转化为二维词频表，将target转化为一维词频表
    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        //用二维数组词频表装所有的贴纸stickers。每一行代表一个贴纸字符串，26列代表字符的分布情况
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        //傻缓存，存放key所需的最小贴纸数量。（注意：无法列dp表，变量是字符串，没有明确的边界条件，只能用傻缓存优化）
        HashMap<String, Integer> dp = new HashMap<>();
        //先在map中加入key->"",value->0。递归调用一直到底后需要用到
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    /**
     * @param stickers   有这些贴纸，二维数组形式
     * @param target          当前目标字符串target
     * @param dp         缓存表
     * @return           返回拼完当前target所需要的最少贴纸数
     */
    public static int process3(int[][] stickers, String target, HashMap<String, Integer> dp) {
        //map表中有直接用
        //若当前target为null，说明此时不需要贴纸，返回0。主函数已经提前设置好了
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        //用一维数组词频表装此时target中字符的分布情况
        int[] tcounts = new int[26];
        char[] cs = target.toCharArray();
        for (char cha : cs) {
            tcounts[cha - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        //1、从第一张贴纸开始尝试，尝试那些包含当前target第一个字符的所有贴纸，然后取最小值
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            // 2、当前贴纸包含当前target第一个字符，才进行调整，避免不必要的遍历。 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[cs[0] - 'a'] > 0) {
                //用来拼接 target在减去当前贴纸所有能用的字符 后剩下的字符
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    //3、检查26个字符的分布情况。确保这个字符需要贴纸来拼
                    if (tcounts[j] > 0) {
                        //4、返回用当前贴纸需要几张。即target中的这个字符有多少个
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            //5、builder装 用这张贴纸后，target还剩下的那些字符
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                //6、选择完这张贴纸后，min=剩下的那些字符返回需要的最小贴纸数。min最终会取所有可能中的最小数
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        //7、若min为maxValue说明无法拼成当前target，返回maxValue，否则返回min + 1，min是剩余字符的最小贴纸数，需要加上当前贴纸+1
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        //8、记录在傻缓存中
        dp.put(target, ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] s = {"a","b"};
        String t = "ab";
        System.out.println(minStickers3(s,t));
    }
}
