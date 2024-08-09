package com.scaler;

import java.util.LinkedList;

public class HashMap<K, V> {
    private int currSize;
    public class HMNode{
        K key;
        V value;
        HMNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private LinkedList<HMNode>[] buckets;
    HashMap(){
        buckets = new LinkedList[10];
        for(int i = 0; i < 10; i++){
            buckets[i] = new LinkedList<>();
        }
    }
    public void put(K key, V value){
        int bucketInd = hashFunction(key);
        HMNode foundAt = find(buckets[bucketInd], key);
        if(foundAt != null){
            foundAt.value = value;
        }
        else{
            HMNode node = new HMNode(key, value);
            buckets[bucketInd].addLast(node);
            this.currSize++;
        }
        double lambda = (this.currSize * 1.0)/(this.buckets.length);
        if(lambda > 2.0)
            reHash();
    }

    private void reHash() {
        LinkedList<HMNode>[] oldBuckets = buckets;
        buckets = new LinkedList[buckets.length * 2];
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = new LinkedList<>();
        }
        for(int i = 0; i < oldBuckets.length; i++) {
            LinkedList<HMNode> curr = oldBuckets[i];
            for (int j = 0; j < curr.size(); j++) {
                HMNode node = curr.get(j);
                this.put(node.key, node.value);
            }
        }
    }

    private HMNode find(LinkedList<HMNode> buckets, K key) {
        for(HMNode node : buckets){
            if(node.key.equals(key))
                    return node;
        }
        return null;
    }

    private int hashFunction(K key) {
        int ind = key.hashCode();
        ind = Math.abs(ind);
        ind = ind % buckets.length;
        return ind;
    }
    public V get(K key){
        int bucketInd = hashFunction(key);
        HMNode foundAt = find(buckets[bucketInd], key);
        if(foundAt != null){
            return foundAt.value;
        }
        return null;
    }
    public V remove(K key){
        int bucketInd = hashFunction(key);
        HMNode foundAt = find(buckets[bucketInd], key);
        if(foundAt != null){
            buckets[bucketInd].remove(foundAt);
            this.currSize--;
            return foundAt.value;
        }
        return null;
    }

    public boolean containsKey(K key){
        int bucketInd = hashFunction(key);
        HMNode foundAt = find(buckets[bucketInd], key);
        if(foundAt != null){
            return true;
        }
        return false;
    }

    public void display(){
        System.out.println("The contents are ");
        for(int i = 0; i < buckets.length; i++) {
            LinkedList<HMNode> curr = buckets[i];
            for (int j = 0; j < curr.size(); j++) {
                System.out.println("Key: " + curr.get(j).key + " value: " + curr.get(j).value);
            }
        }
    }
}