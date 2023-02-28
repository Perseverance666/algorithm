package com.example.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Date: 2023/2/27 17:11
 * 题目：给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 */
public class Code02_CountFiles {
    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber1(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        int files = 0;
        while(!queue.isEmpty()){
            File folder = queue.poll();
            for (File next : folder.listFiles()) {
                if(next.isDirectory()){
                    queue.add(next);
                }
                if(next.isFile()){
                    files++;
                }
            }
        }
        return files;
    }

    public static int getFileNumber2(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "E:\\Java\\Git";
        System.out.println(getFileNumber1(path));
        System.out.println(getFileNumber2(path));
    }
}
