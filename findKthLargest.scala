// https://leetcode.com/problems/kth-largest-element-in-an-array/

import scala.collection.mutable.PriorityQueue  
object Solution {
        
    
/**
    object intOrdering extends Ordering[Int] {
        def compare(a: Int, b: Int) = b compare a 
    }
    
    // O(N*Log(K)) for time complexity 
    // O(K) for space needed for heap 
    def findKthLargest(nums: Array[Int], k: Int): Int = {
        
        val minHeap = new PriorityQueue()(intOrdering)
        for (elem <- nums) {
            if (minHeap.isEmpty || minHeap.size < k) minHeap.enqueue(elem)
            else if (minHeap.head < elem) {
                minHeap.dequeue 
                minHeap.enqueue(elem) 
            }
        }
        //println(minHeap.toString)
        minHeap.head
    }
*/
    // O(N) time for average case. O(N^2) for worst case 
    // O(1) space 
    def findKthLargest(nums: Array[Int], k: Int): Int = {
        var left = 0
        var right = nums.size - 1 
        var newPivotIdx = left
        while (left <= right) {
            val pivotIdx = left + (right - left) / 2
            newPivotIdx = partitionAroundPivot(nums, pivotIdx, left, right)
            if (newPivotIdx == k - 1) return nums(newPivotIdx)
            else if (newPivotIdx > k - 1) right = newPivotIdx - 1
            else if (newPivotIdx < k - 1) left = newPivotIdx + 1 
        }
        nums(newPivotIdx)
    }
    
    def partitionAroundPivot(nums: Array[Int], idx: Int, left: Int, right: Int): Int = {
        println(s"Partition array ${nums.mkString(",")} around value ${nums(idx)}")
        var newIdx = left
        val value = nums(idx)
        // move pivot value to the right
        swap(nums, idx, right)
        for (i <- left to right - 1) {
            if (nums(i) > value) { 
                swap(nums, i, newIdx)
                newIdx +=1
            }
        }
        swap(nums, newIdx, right)
        println(s"Array after partition: ${nums.mkString(",")} new index: ${newIdx} ")
        newIdx
    }
    
    def swap(nums: Array[Int], idx1: Int, idx2: Int) = {
        val temp = nums(idx1)
        nums(idx1) = nums(idx2)
        nums(idx2) = temp
    }
}
