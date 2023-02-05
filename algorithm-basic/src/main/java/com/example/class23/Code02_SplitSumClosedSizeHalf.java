package com.example.class23;

/**
 * @Date: 2023/2/5 13:52
 * 数组分割成累加和尽量相等的两个集合(个数平均)
 * <p>
 * 题目：给定一个正数数组arr，请把arr中所有的数分成两个集合。
 * 如果arr长度为偶数，两个集合包含数的个数要一样多。如果arr长度为奇数，两个集合包含数的个数必须只差一个。
 * 请尽量让两个集合的累加和接近，返回最接近的情况下，较小集合的累加和
 */
public class Code02_SplitSumClosedSizeHalf {
    //1、暴力递归
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        //如果是偶数，返回的累加和必须有 arr.length / 2 个元素
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            //如果是奇数，返回的累加和有 arr.length / 2 个或者 arr.length / 2 + 1个元素，返回离 sum/2最接近的
            int p1 = process(arr, 0, arr.length / 2, sum / 2);
            int p2 = process(arr, 0, arr.length / 2 + 1, sum / 2);
            return Math.max(p1, p2);
        }
    }

    // arr[i....]自由选择，挑选的个数一定要是picks个，累加和<=rest, 离rest最近的返回
    public static int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            //若pick==0，代表之前的选择可以，此时返回累加和0。若pick != 0，代表之前的选择不可以，即这条策略不满足要求，返回-1
            return picks == 0 ? 0 : -1;
        }
        //1、不选当前数
        int p1 = process(arr, i + 1, picks, rest);
        //2、选当前数
        int p2 = -1;
        if (arr[i] <= rest) {
            //2.1、判断当前数是否能选
            int next = process(arr, i + 1, picks - 1, rest - arr[i]);
            if (next != -1) {
                //2.2、判断选完当前数以后，后面的那些数还能否满足要求
                p2 = arr[i] + next;
            }
        }
        return Math.max(p1, p2);
    }

    //2、动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        //返回的累加和要 <= sum /2，先将sum除以2，然后建表
        sum /= 2;
        int N = arr.length;
        //两个集合中的元素个数最大值是 N / 2 向上取整。例N=9,最终的两个集合一个有4个元素，一个有5个，返回最接近sum/2的那个。
        int M = (N + 1) / 2;
        //dp[i][j][k]代表当前讨论第i个数，此时还能选j个元素，返回 <= k的累加和
        int[][][] dp = new int[N + 1][M + 1][sum + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    //先都设置成无效值-1
                    dp[i][j][k] = -1;
                }
            }
        }
        //index = N此时越界，pick==0，代表之前的选择可以，此时返回累加和0。
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    //1、不选当前值
                    int p1 = dp[index + 1][picks][rest];
                    //2、选当前值
                    int p2 = -1;
                    //2.1、判断当前数是否能选
                    if (picks - 1 >= 0 && arr[index] <= rest) {
                        int next = dp[index + 1][picks - 1][rest - arr[index]];
                        if(next != -1){
                            //2.2、判断选完当前数以后，后面的那些数还能否满足要求
                            p2 = arr[index] + next;
                        }
                    }
                    dp[index][picks][rest] = Math.max(p1,p2);
                }
            }
        }
        //如果是偶数，返回的累加和必须有 arr.length / 2 个元素
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];      //这里sum已经除以2了
        } else {
            //如果是奇数，返回的累加和有 arr.length / 2 个或者 arr.length / 2 + 1个元素，返回离 sum/2最接近的
            int p1 = dp[0][arr.length / 2][sum];
            int p2 = dp[0][arr.length / 2 + 1][sum];
            return Math.max(p1, p2);
        }

    }


//	public static int right(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return 0;
//		}
//		int sum = 0;
//		for (int num : arr) {
//			sum += num;
//		}
//		return process(arr, 0, 0, sum >> 1);
//	}
//
//	public static int process(int[] arr, int i, int picks, int rest) {
//		if (i == arr.length) {
//			if ((arr.length & 1) == 0) {
//				return picks == (arr.length >> 1) ? 0 : -1;
//			} else {
//				return (picks == (arr.length >> 1) || picks == (arr.length >> 1) + 1) ? 0 : -1;
//			}
//		}
//		int p1 = process(arr, i + 1, picks, rest);
//		int p2 = -1;
//		int next2 = -1;
//		if (arr[i] <= rest) {
//			next2 = process(arr, i + 1, picks + 1, rest - arr[i]);
//		}
//		if (next2 != -1) {
//			p2 = arr[i] + next2;
//		}
//		return Math.max(p1, p2);
//	}
//
//	public static int dp1(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return 0;
//		}
//		int sum = 0;
//		for (int num : arr) {
//			sum += num;
//		}
//		sum >>= 1;
//		int N = arr.length;
//		int M = (arr.length + 1) >> 1;
//		int[][][] dp = new int[N + 1][M + 1][sum + 1];
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= M; j++) {
//				for (int k = 0; k <= sum; k++) {
//					dp[i][j][k] = -1;
//				}
//			}
//		}
//		for (int k = 0; k <= sum; k++) {
//			dp[N][M][k] = 0;
//		}
//		if ((arr.length & 1) != 0) {
//			for (int k = 0; k <= sum; k++) {
//				dp[N][M - 1][k] = 0;
//			}
//		}
//		for (int i = N - 1; i >= 0; i--) {
//			for (int picks = 0; picks <= M; picks++) {
//				for (int rest = 0; rest <= sum; rest++) {
//					int p1 = dp[i + 1][picks][rest];
//					int p2 = -1;
//					int next2 = -1;
//					if (picks + 1 <= M && arr[i] <= rest) {
//						next2 = dp[i + 1][picks + 1][rest - arr[i]];
//					}
//					if (next2 != -1) {
//						p2 = arr[i] + next2;
//					}
//					dp[i][picks][rest] = Math.max(p1, p2);
//				}
//			}
//		}
//		return dp[0][0][sum];
//	}

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
