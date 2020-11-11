package com.linus.lab.algorithm.trie;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/11/10 15:45
 * @Description TODO
 */
public class WordSearchII {

    private int xLenght, yLength;

    private boolean inRange(int x, int y) {
        return x >= 0 && x < xLenght && y >= 0 && y < yLength;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        Arrays.stream(words).forEach(root::insert);

        Queue<Task> queue = new LinkedList<>();
        xLenght = board.length;
        yLength = board[0].length;
        for (int i = 0; i < xLenght; i++) {
            for (int j = 0; j < yLength; j++) {
                queue.offer(new Task(i, j, root, new ArrayList<>()));
            }
        }

        Set<String> res = new HashSet<>();
        while (!queue.isEmpty()) {
            Task task = queue.poll();

            int index = board[task.x][task.y] - 'a';
            Trie nextNode = task.node.sub[index];
            Pair<Integer, Integer> pair = new Pair<>(task.x, task.y);
            if (nextNode != null && !task.memo.contains(pair)) {
                task.memo.add(pair);
                if (nextNode.isWord) {
                    StringBuilder sb= new StringBuilder();
                    task.memo.stream().map(p->board[p.getKey()][p.getValue()]).forEach(sb::append);
                    res.add(sb.toString());
                }
                if (inRange(task.x, task.y + 1)) {
                    queue.offer(new Task(task.x, task.y + 1, nextNode,
                            (ArrayList<Pair<Integer, Integer>>) task.memo.clone()));
                }

                if (inRange(task.x, task.y - 1)) {
                    queue.offer(new Task(task.x, task.y - 1, nextNode,
                            (ArrayList<Pair<Integer, Integer>>) task.memo.clone()));
                }

                if (inRange(task.x + 1, task.y)) {
                    queue.offer(new Task(task.x + 1, task.y, nextNode,
                            (ArrayList<Pair<Integer, Integer>>) task.memo.clone()));
                }

                if (inRange(task.x - 1, task.y)) {
                    queue.offer(new Task(task.x - 1, task.y, nextNode,
                            (ArrayList<Pair<Integer, Integer>>) task.memo.clone()));
                }


            }
        }
        return new ArrayList<>(res);
    }

    private class Task {
        int x, y;
        Trie node;
        ArrayList<Pair<Integer, Integer>> memo;

        public Task(int x, int y, Trie node, ArrayList<Pair<Integer, Integer>> memo) {
            this.x = x;
            this.y = y;
            this.node = node;
            this.memo = memo;
        }
    }

    private class Trie {

        Trie[] sub;
        boolean isWord;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            sub = new Trie[26];
            isWord = false;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word.length() == 0) {
                isWord = true;
                return;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) sub[index] = new Trie();

            sub[index].insert(word.substring(1));
        }

    }

    public static void main(String[] args) {
        WordSearchII o = new WordSearchII();

        List<String> res = o.findWords(new char[][]{
                new char[]{'o','a','a','n'},
                new char[]{'e','t','a','e'},
                new char[]{'i','h','k','r'},
                new char[]{'i','f','l','v'}
        }, new String[] {"oath","pea","eat","rain"});

        System.out.println(res);
    }
}
