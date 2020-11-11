package com.linus.lab.algorithm.trie;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/11/10 15:45
 * @Description TODO
 */
public class WordSearchIIOnline {
    class TrieNode{ //树的节点类
        TrieNode [] next;
        String isWord;
        TrieNode(){
            this.isWord = "";
            this.next = new TrieNode [26];
        }
    }
    private class Trie{//树类，包含2个方法，在树中插入单词以及检查当前结点是否包含某个字符的子节点
        private TrieNode root;
        Trie(){
            this.root = new TrieNode();
        }
        public void insert(String x){
            TrieNode curr = root;
            for(int i=0;i<x.length();i++){
                TrieNode node = curr.next[x.charAt(i)-'a'];
                if(node==null){
                    node = new TrieNode();
                    curr.next[x.charAt(i)-'a'] = node;
                }
                curr = node;
                if(i==x.length()-1)     curr.isWord = x;
            }
        }
        public boolean contains(char x,TrieNode root){
            if(root.next[x-'a']!=null)  return true;
            else return false;
        }
    }
    List<String> res = new ArrayList<>();
    Trie tree = new Trie();
    int []x = new int []{-1,1,0,0};
    int [] y = new int []{0,0,-1,1};
    public List<String> findWords(char[][] board, String[] words) {
        for(String word:words){//遍历words构建树
            tree.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(tree.contains(board[i][j],tree.root)){//如果树的根节点的子节点包含字符，开始搜索是否有这个单词
                    dfs(board,i,j,tree.root);
                }
            }
        }
        return res;
    }
    private void dfs(char[][] board,int i, int j, TrieNode root){
        TrieNode node = root.next[board[i][j]-'a'];
        if(!node.isWord.equals("")){//如果单词标记不为空，则添加，并将其置空
            res.add(node.isWord);
            node.isWord = "";
        }
        char ss = board[i][j];
        board[i][j] = '.';//防止多次访问
        for(int index=0;index<4;index++){
            int newi = i+x[index];
            int newj = j+y[index];
            if(newi<0||newj<0||newi>=board.length||newj>=board[0].length||board[newi][newj]=='.')    continue;
            if(tree.contains(board[newi][newj],node))   dfs(board,newi,newj,node);
        }
        board[i][j] = ss;//回溯
    }
    public static void main(String[] args) {
        WordSearchIIOnline o = new WordSearchIIOnline();

        long t1 = System.currentTimeMillis();
        List<String> res = o.findWords(new char[][]{
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'}
        }, new String[] {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});


//        List<String> res = o.findWords(new char[][]{
//                new char[]{'o','a','a','n'},
//                new char[]{'e','t','a','e'},
//                new char[]{'i','h','k','r'},
//                new char[]{'i','f','l','v'}
//        }, new String[] {"oath","pea","eat","rain"});

        System.out.println(res);

        System.out.println(System.currentTimeMillis() - t1);

    }
}
