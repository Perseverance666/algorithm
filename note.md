# 算法笔记

## 一、基础+进阶

### 1、class01

#### 1）选择排序

1.先从a[0] - a[N-1] 个数中找到最小的数把它放在a[0]的位置

2.在从a[1] - a[N-1]个数中找到最小的数把它放在a[1]的位置上，以此类推

3.一次循环找到一个最小值，放在开头，然后每次循环找一个最小值往之前找到的最小值后面放，复杂度为O(N^2)

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

#### 2）冒泡排序

1.先从a[0] - a[N-1]个数中，从头开始两两比较，将较大的数放到后面，这样最后一个数就是最大的

2.再从a[0] - a[N-2]个数中，像上面步骤一样两两比较，这样倒数第二个数就是第二大的，以此类推

3.一次循环两两比较，最终将找到一个最大值放在最后，然后每次循环再找剩下数中的最大值，复杂度为O(N^2)

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

#### 3）插入排序

1.先确保0 - 1 位置上的数有序，若a[1] 小于a[0]，交换，反之不变

2.在确保0 - 2 位置上的数有序，若a[2] 小于a[1]，交换，直到比前面的数大，或者前面没数了，以此类推

3.这样确保第i个数前面的数都是有序的，然后将该数插入到合适位置，复杂度为O(N^2)

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
