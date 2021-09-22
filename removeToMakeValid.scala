# https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/submissions/

import scala.collection.mutable.{Stack, ListBuffer}

object Solution {
    
    def minRemoveToMakeValid(s: String): String = {
        // First pass to detect index to remove which is the position of close parentheses 
        val indexes_to_remove = new ListBuffer[Int]()
        val st = new Stack[Int]()
        
        var i = 0 
        for (i <- 0 to s.size-1) {
            if(s(i) == '(') {
                st.push(i)  
            } else if (s(i) == ')') {
                if (st.size > 0) {
                    st.pop()
                } else {
                    indexes_to_remove.append(i)
                }
            }
        }
        // If stack has remaining index, then that's the one to remove to 
        while (st.size > 0) {
           indexes_to_remove.append(st.pop())
        }
        
        val str = new StringBuilder()
        
        for (i <- 0 to s.size - 1) {
            if (!indexes_to_remove.contains(i)) {
                str += s(i)
            }
        }
        return str.toString()
    }
}
