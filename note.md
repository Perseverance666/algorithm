# 算法笔记

## 一、基础+进阶

### 1、class01

#### 1）选择排序，复杂度O(N^2)

1.先从a[0] - a[N-1] 个数中找到最小的数把它放在a[0]的位置

2.在从a[1] - a[N-1]个数中找到最小的数把它放在a[1]的位置上，以此类推

3.一次循环找到一个最小值，放在开头，然后每次循环找一个最小值往之前找到的最小值后面放

```java
    public static void SelectionSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大选择排序
        for (int i = 0; i < arr.length; i++) {
            //先找一个当做最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);
        }
    }
```

#### 2）冒泡排序，复杂度O(N^2)

1.先从a[0] - a[N-1]个数中，从头开始两两比较，将较大的数放到后面，这样最后一个数就是最大的

2.再从a[0] - a[N-2]个数中，像上面步骤一样两两比较，这样倒数第二个数就是第二大的，以此类推

3.一次循环两两比较，最终将找到一个最大值放在最后，然后每次循环再找剩下数中的最大值

```java
    public static void BubbleSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大冒泡排序  0 ~ N-1    0 ~ N-2  ...
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j + 1, j);
                }
            }
        }

    }
```

#### 3）插入排序，复杂度O(N^2)

1.先确保0 - 1 位置上的数有序，若a[1] 小于a[0]，交换，反之不变

2.在确保0 - 2 位置上的数有序，若a[2] 小于a[1]，交换，直到比前面的数大，或者前面没数了，以此类推

3.这样确保第i个数前面的数都是有序的，然后将该数插入到合适位置

```java
    public static void insertionSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大插入排序 0~1   0~2 ... 最后0~N-1 上都是有序的
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j - 1] > arr[j]){
                    swap(arr, j-1, j);
                }
            }
        }
    }
```

#### 4）二分法

##### 1、题目一

题目在一个有序数组中，找某个数是否存在

1.先找中间的数，若当前数 = 指定数，找到了

2.若当前数 < 指定数，说明所求可能在右边，将L移到mid右边

3.若当前数 > 指定数，说明所求可能在左边，将R移到mid左边

4.当L> R时，就全找完了，没有找到，返回false

```java
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L <= R){
            mid = L + ((R - L) >> 1); //等同于 mid = (L + R) / 2;
            if(sortedArr[mid] == num){
                return true;
            } else if(sortedArr[mid] < num){
                L = mid + 1;
            } else {
                R = mid - 1 ;
            }
        }
        //全都比较完了，没有找到
        return false;
    }
```

##### 2、题目二

题目：在一个有序数组中，找到 >= 某个数最左侧的位置 (<= 最右侧位置 问题类似)

1.先找中间的数，若当前数 >= 指定数，说明左面有可能还有符合要求的数，记录当前位置，并将R移到mid左边

2.若当前数 < 指定数，说明左边肯定没有了，不记录当前位置，并将L移到mid右边，继续向右找

3.当L> R时，就找完了，index存的就是所求

```java
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;
        while(L <= R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] >= value){
                //若当前数 >= 指定数，记录当前位置，并将R移到mid左边
                index = mid;
                R = mid - 1;
            }else{
                //若当前数 < 指定数，不记录当前位置，并将L移到mid右边
                L = mid + 1;

            }
        }
        return index;
    }
```

##### 3、题目三

题目：局部最小值问题，找一个局部最小即可

1.先判断第0个数，或者第N-1个数是否是局部最小

2.以上两个数不满足，开始二分，若mid大于左边的数，不管是否大于还是小于右边的数，mid左边一定存在局部最小

3.若mid大于右边的数，不管是否大于还是小于左边的数，mid右边一定存在局部最小

4.当L=R时，这个数一定是局部最小

```java
    public static int getLessIndex(int[] arr) {
        if(arr == null || arr.length <= 0){
            return -1; //不存在
        }
        if(arr.length == 1 || arr[0] < arr[1]){
            return 0; //0位置就是局部最小
        }
        if(arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;  //arr.length -1 位置就是局部最小
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while(L < R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] < arr[mid -1] && arr[mid] < arr[mid + 1]){
                return mid;
            }else if(arr[mid] > arr[mid -1]){
                //说明局部最小在左边
                R = mid - 1;
            }else{
                //说明局部最小在右边
                L = mid + 1;
            }
        }
        //此时L = R,这个数一定是局部最小
        return L;
    }
```

### 2、class02

#### 1）异或运算

技巧：如何不用额外变量交换两个数

```java
    public static void swap (int[] arr, int i, int j) {
        arr[i]  = arr[i] ^ arr[j];
        arr[j]  = arr[i] ^ arr[j];
        arr[i]  = arr[i] ^ arr[j];
    }
```

##### 1、题目一

题目：一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数

定义一个变量eor初值为0，将所有数进行异或运算，出现偶数次的其他数经过异或运算变成0，最终只剩出现奇数次的那个数，即为所求

```java
public static void printOddTimesNum1(int[] arr) {
    int eor = 0;
    for (int i = 0; i < arr.length; i++) {
        eor ^= arr[i];
    }
    System.out.println(eor);
}
```

##### 2、题目二

题目：一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数

1.定义一个变量eor初值为0，将所有数进行异或运算，出现偶数次的其他数经过异或运算变成0，最后eor = a ^ b

2.我们知道a != b，故eor二进制状态下一定存在1，只有a与b的某一位不同，eor才等于1，我们取eor最右边的1这位进行研究，此时a和b的这一位一定是一个1一个0

3.将原来的所有数分成两组，一组是那一位是1的，另外一组是那一位是0的，这样就将a和b分成了两组，各自再带几个出现偶数次的数

4.然后再定义一个变量eor2，规定只对那一位是1的进行异或，这样经过异或运算最终假如就得到a了，然后b = eor ^ a =  a ^ b

```java
public static void printOddTimesNum2(int[] arr) {
    int eor = 0;
    for(int i = 0; i < arr.length; i++){
        eor ^= arr[i];
    }
    System.out.println(eor);  //eor = a ^ b;

    //取eor最右边的1最为条件，然后分成两组
    int rightOne = eor & (-eor);   //eor & (~eor + 1)    等同于  eor & (-eor) ，可以得到最右边的1，例如：0001000
    int eor2 = 0;
    for(int i = 0; i < arr.length; i++){
        //这次只异或那一位是1的
        if((arr[i] & rightOne) == rightOne){
            eor2 ^= arr[i];
        }

    }

    int a = eor2; //eor2的异或结果就是其中一个数
    int b = eor ^ a;   //b = eor ^ a = a ^ b ^ a;
    System.out.println(a + " " + b);

}
```

##### 3、题目三

题目：数组中所有的数都出现了M次，只有一种数出现了K次，1 <= K < M 返回这种数

1.先创建一个32位的数组，开始存的全是0

2.然后依次检查原数组中的每个数，每个数的哪一位上有1((arr[i] >> j) & 1) != 0)，就在32位数组上的对应位置加1，最后完成32位数组

3.检查32位数组，若某一位模M 等于 0，说明出现K次的那个数在这一位上是0，若模M不等于0，说明出现K次的那个数在这一位上是1，把这一位的1加到结果上去 ans = ans | (1 << i)。最终找到这个数

```java
  public static int km(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                if(((arr[i] >> j) & 1) != 0){
                    //说明从右向左数，第j位是1，加到32位数组中
                    t[j]++;
                }

            }
        }

        int ans = 0;
        for(int i = 0; i < 32; i++){
            if(t[i] % m != 0){
                //说明出现k次的那个数，从右往左，二进制在第i位上是1
                ans = ans | (1 << i);
            }
        }

        return ans;
    }
```

### 3、class03

#### 1）链表反转

##### 1、单链表反转

1.准备两个指针pre和next都先指向null，他俩用来记录位置，先让next记录当前节点的下一结点

2.然后反转指针

3.然后pre指向Head记录当前节点位置，即下一个节点的上一个节点

4.最后更新Head，此时第一个节点完成，依次完成之后的节点

```java
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
```

##### 2、双链表反转

双链表和单链表过程一样，只是反转链表时，要反转next和last两个

```java
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
```

#### 2）移除链表元素

1.首先让head指向第一个不需要删除的节点，用来返回

2.然后创建两个指针pre，cur先指向head（cur用来指向当前节点，pre用来指向下一个节点）

3.判断该节点值是否与指定值相等。若相等，让pre跳过这个节点，pre不动，pre.next指向下一个节点，然后cur后移。确保cur指向下一个节点，pre指向当前节点

4.若不等，pre指向cur指向的节点（因为cur和pre有可能指向同一个位置，也有可能一前一后)，目的是让pre指向cur指向的位置，然后cur后移。确保cur指向下一个节点，pre指向当前节点



```java
    public static Node removeValue(Node head, int num) {
        //1、首先让head指向第一个不需要删除的节点
        while(head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        //2、然后进行删除
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if(cur.value == num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

```

#### 3）返回栈中最小元素

1.首先定义两个栈，一个存数据，另一个存最小值

2.若当前元素比当前最小值小，当前元素入两个栈

3.若当前元素大于当前最小值，当前元素入数据栈，另一个栈再存一次之前的最小值，保证两个栈有相同的层次

```java
 public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }
```

#### 4）用栈实现队列

1.准备两个栈，push栈用来加数据，pop栈用来弹出数据

2.首先将push栈中的所有数据全都弹出到pop栈中，这样pop栈弹出就实现队列了

3.若在pop栈弹出的过程中，push栈又加入了新的元素。必须等pop栈都弹出完了，才能将push栈的元素弹出到pop栈中

4.模拟添加，先加入push栈，等pop栈为空时，再全部添加到pop栈

5.模拟弹出，等pop栈为空时，再全部添加到pop栈，然后弹出pop栈，模拟完成

```java
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            //只有pop栈为空时，才将push栈的所有元素弹出到pop栈中
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    //一次将push栈中的所有元素都弹出到pop栈中
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
```

#### 5）用队列实现栈

1.首先准备两个队列，一个queue最终用来弹出的，另一个help用来暂时存放其他元素的

2.模拟添加，直接往queue队列中加入元素即可

3.模拟弹出，先将queue队列的元素弹出到help队列中，让queue只剩一个元素，这样弹出queue中的元素就实现了栈。

4.queue队列弹出完这一个元素后，将help队列改成queue队列，将queue队列改成help队列

```java
    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }
```

#### 6）Master公式

形如T(N)=a*T(N/b) + O(N^d) (其中的a、b、d都是常数)的递归函数并且子规模一致的条件下，可以直接通过Master公式来确定时间复杂度

1.如果log(b,a) < d，复杂度为O(N ^ d)

2.如果log(b,a) > d，复杂度为O(N ^ log(b,a))

3.如果log(b,a) = d，复杂度为O(N ^ d * log(2,N))

### 4、class04

#### 1）归并排序，复杂度O(N*logN)

##### 1、递归实现

1.定义一个函数process(int[] arr, int L, int R)，把L到R上变有序

2.然后分成两部分把L到M变有序，再把M+1到R变有序，然后再把它们两个merge

3.merge(int[] arr, int L, int M, int R)，准备一个数组help用来存最后排序好的数据。准备两个变量p1和p2首先分别指向L和M+1，然后经过挑选完成最终排序

```java
    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 把arr[L..R]排有序
    public static void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    //将L到M 和 M+1到R上的数据进行归并排序
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //将help数组数据存回arr
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }
```

##### 2、非递归实现

1.首先步长设置为1，然后进入循环判断此时步长是否超出数组长度，若超出了证明排序完成

2.先从左边第一个数，即L=0开始，然后再进入循环判断此时L是否超出数组长度，若超出证明越界了。循环结束后还要检测一下，看看此时步长是否 > 2分之数组长度，防止溢出（因为有可能由于精度问题导致越界，结果变成负数，这样就又进入第一个循环了，所以要检测一下）。若没有越界，则将步长乘2

3.在第二个循环中，若此时剩下的元素数量(N-L) <= 步长，就结束当前循环。否则确定M位置和R位置，其中要是确定的R位置已经越界了就改成N-1

4.然后合并merge(int[] arr, int L, int M, int R)，修改L的位置，L = R + 1

```java
    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) { //步长小于数组长度才继续
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {		//L越界就结束循环
                if (mergeSize >= N - L) {	//剩下的元素 <= 步长就结束循环
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - 1 - M);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }
```

#### 2）返回数组的最小和，归并排序

将数组中每个元素左边比它小的**值**全部累加起来。左值小于右值才返回

1.定义一个函数process(int[] arr, int L, int R)，把0到N-1上变有序，并返回最小和

2.先返回L到M上的最小和，再返回M+1到R上的最小和，最后merge两部分再求最小和，把这三部分最小和加起来

3.merge将元素放到help数组时，如果左侧指定值 < 右侧指定值，说明左值是 右值以及右边剩下数 的最小和中的一个值，左值在 右值以及右边剩下数 的所有最小和中，占据的总值为：左值 * （右侧剩下数的个数）。左侧指针移动，边计算最小和边merge

4.如果左值 > 右值，只将较小数放到help，右侧指针移动，最小和不加，继续比较接下来的

5.如果左值 = 右值，将右值放入help，右侧指针移动，最小和不加。(要确保左右值不等时，能知道右侧有多少个比左值大的，所以要右侧指针移动)

```java
 public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
```

#### 3）返回逆序对数量，归并排序

将数组中每个元素右边比它小的数的**个数**全部累加起来。左值大于右值才返回

1.定义一个函数process(int[] arr, int L, int R)，把0到N-1上变有序，并返回逆序对数量

2.先返回L到M上的逆序对数量，再返回M+1到R上的逆序对数量，最后merge两部分再求逆序对数量，把这三部分逆序对数量加起来

3.这次的merge指针从右往左，merge将元素放到help数组时，若左值 > 右值，说明左值和 右值及右值左边的所有数 都可构成逆序对，左指针向左移动检验下一个左值。边计算逆序对数量边merge

4.如果左值 < 右值，只将较小数放到help，右侧指针左移，逆序对数量不加，继续比较接下来的

5.如果左值 = 右值，将右值放入help，右侧指针左移，逆序对数量不加。(要确保左右值不等时，能知道右侧有多少个比左值小的，所以要右侧指针移动)

```java
public static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    //这次指针从右边开始移动
    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= L && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
```

#### 4）翻转对，归并排序

题目：给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。返回给定数组中的重要翻转对的数量

1.定义一个函数process(int[] arr, int L, int R)，把0到N-1上变有序，并返回翻转对数量

2.先返回L到M上的翻转对数量，再返回M+1到R上的翻转对数量，最后merge两部分再求翻转对数量，把这三部分翻转对数量加起来

3.这次的先求翻转对数量，再进行merge。利用for循环指向左侧的变量，再定义一个变量指向右侧的变量，初始值为m+1，如果左值 > 2 * 右值，右侧指针右移，直到不满足停下，翻转对数量 += 右侧指针 - (M+1)。先计算翻转数量再merge

4.然后左侧指针移动，以此类推。最后merge

```java
 public static int reversePairs(int[] nums) {
        if(nums == null || nums.length <= 1){
            return 0;
        }
        return process(nums,0,nums.length - 1);
    }

    public static int process(int[] arr,int L,int R){
        //边界条件
        if(L == R){
            return 0;
        }
        int M = (L + R) / 2;
        //先计算左侧数量，再计算右侧数量，然后计算merge数量，最后三者加起来
        return process(arr,L,M) + process(arr,M+1,R) + merge(arr,L,M,R);
    }

    
    public static int merge (int[] arr,int L,int M,int R){
        int ans = 0;
        int windowR = M + 1;  //用来追踪右侧
        //用一个for循环来追踪左侧
        for(int i = L;i <= M; i++){
            //1、先计算翻转对
            while(windowR <= R && (long)arr[i] > (long)2 * arr[windowR]){
                windowR++;
            }
            ans += windowR - (M + 1);
        }

        //2、再进行merge
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //一侧都排完了
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        //将排好序的help数组拷贝到arr中
        for(i = 0;i < help.length; i++){
            arr[L + i] = help[i];
        }

        return ans;
    }
```

### 5、class05

#### 1）区间和的个数，归并排序，难

题目：给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的区间和的个数 。区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

1.该问题要转换成 利用前缀数组来求区间和的个数

2.定义一个函数process(long[] sum, int L, int R, int lower, int upper)，返回0 ~ N-1上满足条件的区间和数量

3.边界条件L==R，即求L这个位置上的区间和数量，若这个数满足[lower,upper]，说明对于原数组0~L这是一个区间和，区间和数+1。若不满足，区间和数+0，继续接下的操作

4.先求L到M上的区间和数，再求M+1到R上的区间和数，然后求merge后的区间和数，最后加起来

5.这次先求区间和数，再进行merge。目标就是求右侧每个元素的区间和个数的总和。两个变量追踪左侧，min =  右数 - upper ,max = 右数 - lower，若左数在[min,max]之间，则(左数,右数)这个区间和就满足条件，利用两个变量追踪 求所有满足条件的左数

6.然后再merge

```java
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //转换为利用前缀数组求区间和个数
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for(int i = 1; i < nums.length;i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        //返回前缀数组0 ~ N-1上的区间和个数
        return process(sum,0,sum.length - 1,lower,upper);
    }
     
    public static int process(long[] sum,int L,int R,int lower,int upper){
        if(L == R){
            //若L这个数满足，即对于原数组(0,L)这个区间和满足条件，区间和+1，否则+0
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = (L + R) / 2;
        //先求左侧区间和数，再求右侧区间和数，然后求merge后的区间和数，最后加起来
        return process(sum,L,M,lower,upper) + process(sum,M+1,R,lower,upper) + merge(sum,L,M,R,lower,upper);

    }

    public static int merge(long[] sum,int L,int M,int R,int lower,int upper){
        //1、先计算区间和数
        int ans = 0;   //区间和数
        //定义两个变量追踪左侧，用来判断右侧的每个数的区间和数
        int windowL = L;
        int windowR = L;
        //定义一个循环来，追踪右侧每个数  [windowL, windowR)
        for(int i = M + 1; i <= R; i++){
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            //若左数在[min,max]之间，则(左数,右数)这个区间和就满足条件
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            //此时windowL ~ windowR是满足条件的，即(windowL,右数) 一直到 (windowR,右数)这些区间和都是满足条件的
            ans += windowR - windowL;
        }
        //2、再merge
        long[] help = new long[R-L+1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while(p1 <= M && p2 <= R){
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while(p1 <= M){
            help[i++] = sum[p1++];
        }
        while(p2 <= R){
            help[i++] = sum[p2++];
        }
        for(i = 0; i < help.length; i++){
            sum[L+i] = help[i];
        }

        return ans;
    }
```

#### 2）荷兰国旗问题的划分

题目：将小于最后的数arr[R]，等于arr[R]和大于arr[R]的数分成三个区域，排好后 返回等于arr[R]的区间

1.设置小于区域 less = L -1和 大于区域more = R，大于区域先把arr[R]囊括起来，最后再来处理

2.定义个变量用来追踪当前数，如果当前数 = arr[R]，追踪下一个数

3.如果当前数 < arr[R] ，当前数与小于区域右边的那一数互换，然后区域向右移，追踪下一个数

4.如果当前数 > arr[R] ，当前数与大于区域左边的那一数互换，然后区域向左移，不追踪下一个数

5.最后将arr[R]与 大于区域 第一个数互换。最终返回的区间是 (小于区域 + 1,大于区域)，因为大于区域的第一个数是之前的最后的那个arr[R]

```java
    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    //返回最终排好的 等于arr[R]的那些数的坐标区间(都在中间位置)
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) { // L...R L>R
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < 区 右边界
        int more = R; // > 区 左边界
        int index = L;
        while (index < more) { // 当前位置，不能和 >区的左边界撞上
            //如果当前数 = arr[R]，追踪下一个数
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                //如果当前数 < arr[R] ，当前数与区域前一个数互换，然后区域向后移，追踪下一个数
				swap(arr, less + 1, index);
				less++;
				index++;
            } else {
                //如果当前数 > arr[R] ，当前数与区域前一个数互换，然后区域向左移，不追踪下一个数
                swap(arr, index, more - 1);
                more--;
            }
        }
        //最后将arr[R]与 大于区域 第一个数互换
        swap(arr, more, R); // <[R]   =[R]   >[R]
        //返回的区间是 (小于区域 + 1,大于区域)，因为大于区域的第一个数是之前的最后的那个arr[R]
        return new int[] { less + 1, more };
    }
```

#### 3）随机快速排序，复杂度O(N*logN)

普通快速排序最差情况复杂度O(N^2)

1.调用方法process3(int[] arr, int L, int R)，将0~N-1 上的元素进行随机快速排序

2.边界条件L >= R返回上一层

3.先将最后一个数与前面随机一个数进行互换 变成随机快速排序

4.然后通过netherlandsFlag方法 将以arr[R]进行分割3部分，然后返回 囊括所有的arr[R] 的区间

5.最后递归调用process方法，先完成(L，区间左值 -1)上的随机快速排序，再完成(区间右值+1，R)上的随机快速排序

```java
	// 荷兰国旗问题
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1;
		int more = R;
		int index = L;
		while (index < more) {
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
				swap(arr, index++, ++less);
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, more, R);
		return new int[] { less + 1, more };
	}
   
	public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }
```

### 6、class06

#### 1）堆

堆本质是一棵完全二叉树，完全二叉树只要有右孩子就必须有左孩子，它包括大根堆和小根堆，大根堆是每个子树的根节点都比孩子节点要大，这样第一个根节点数就是最大的

堆的相关操作：

```java
// 新加进来的数，现在停在了index位置，若它爹小于它，依次往上移动，
//向上调整
private void heapInsert(int[] arr, int index) {
    // [index] [index-1]/2
    // index == 0
    while (arr[index] > arr[(index - 1) / 2]) {
        //若新加的数大于它爹，与它爹交换位置，再去比较它与它的新爹谁大，循环下去，直到它爹>=它
        swap(arr, index, (index - 1) / 2);
        index = (index - 1) / 2;
    }
}

// 背景：将第一个元素与最后一个元素互换后，heapSize--，逻辑删除最大元素。
// 检验此时第一个数 下面它大是否比孩子大。若它比下面的孩子小。从index位置，它不断的下沉
// 直到 左右孩子中较大的孩子都不再比index位置的数大 或者 已经没孩子了，结束
//向下调整
private void heapify(int[] arr, int index, int heapSize) {
    int left = index * 2 + 1;
    while (left < heapSize) { // 如果有左孩子，有没有右孩子，可能有可能没有！
        //1、先取左右孩子中较大的。如果有右孩子并且右孩子大于左孩子选右孩子。如果没有右孩子或者左孩子大于右孩子选左孩子
        int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
        //2、然后将孩子与它爹比较。 把爹和孩子中较大的下标，给largest。
        largest = arr[largest] > arr[index] ? largest : index;
        if (largest == index) {
            //3、若它爹就是较大的，不用交换，结束循环
            break;
        }
        //4、若孩子大于爹，交换位置，把大的放上面。然后继续检查它的孩子是否比它大，循环下去
        swap(arr, largest, index);
        index = largest;
        left = index * 2 + 1;
    }
}
```

#### 2）堆排序，复杂度O(N*logN)

```java
public static void heapSort(int[] arr) {
    if(arr == null || arr.length <= 1){
        return;
    }

    int heapSize = arr.length;      //满足大根堆的这些数的数量
    //1、先把数组从0开始依次heapInsert
    for(int i = 0; i < heapSize; i++){
        heapInsert(arr,i);
    }
    //2、然后把第一个元素也就是最大的元素与最后一个元素交换，同时heapSize有效值减一，逻辑删除最大值，最大值排好序了
    swap(arr,0,heapSize - 1);
    heapSize--;

    while(heapSize > 0){
        //3、对新换上来的根节点向下heapify
        heapify(arr,0,heapSize);
        //4、heapify后又变成大根堆，再把第一个元素和最后一个元素交换，这样又一个第二大的排好序了，直到heapSize变为0
        swap(arr,0,heapSize - 1);
        heapSize--;
    }
}

// arr[index]刚来的数，往上移动
public static void heapInsert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
        swap(arr, index, (index - 1) / 2);
        index = (index - 1) / 2;
    }
}

// arr[index]位置的数，能否往下移动
public static void heapify(int[] arr, int index, int heapSize) {
    int left = index * 2 + 1; // 左孩子的下标
    while (left < heapSize) { // 下方还有孩子的时候
        // 两个孩子中，谁的值大，把下标给largest
        // 1）只有左孩子，left -> largest
        // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
        // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
        int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
        // 父和较大的孩子之间，谁的值大，把下标给largest
        largest = arr[largest] > arr[index] ? largest : index;
        if (largest == index) {
            break;
        }
        swap(arr, largest, index);
        index = largest;
        left = index * 2 + 1;
    }
}
```

#### 3）部分有序数组变有序

给定一个数组，部分有序，意思就是现在数组元素的位置 距离 排好顺序后的位置 不超过k个单位

思想：最小的数一定在(0,k)上，因为若在k+1或者以后，最小数移动k位就无法到0的位置上了。第二小的一定在(1,k+1)，以此类推。这样 堆加一个，弹一个，因为是小根堆，弹出来的都是当前堆中最小的

```java
public static void sortedArrDistanceLessK(int[] arr, int k) {
    //若k=0 说明已经排好序了
    if (k == 0) {
        return;
    }
    // 默认小根堆
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int index = 0;  //放入堆中的元素下标
    // 最小的数一定在(0,k)上，因为若在k+1或者以后，最小数移动k位就无法到0的位置上了
    //1、先把(0,k-1)上的数放入小根堆里
    for (; index <= Math.min(arr.length - 1, k - 1); index++) {
        heap.add(arr[index]);
    }

    //2、再把k上的数到堆里，然后弹出堆的第一个元素就是最小值，放进新数组。然后依次往后，堆加一个，弹一个
    int i = 0;	//排好序数组的下标
    for (; index < arr.length; i++, index++) {

        heap.add(arr[index]);
        arr[i] = heap.poll();
    }
    //3、等到元素都已经加过堆里面了，就把堆剩余的元素都弹出来，就是排好序的
    while (!heap.isEmpty()) {
        arr[i++] = heap.poll();
    }
}
```

