public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
     PriorityQueue<Integer> maxHeap;
     PriorityQueue<Integer> minHeap;
     int totalNum;
     
    public int[] medianII(int[] nums) {
        // write your code here
        int[] res = new int[nums.length];
        if(nums == null || nums.length == 0) return res;
        
        Comparator<Integer> heapCmp = new Comparator<Integer>(){
          @Override
          public int compare(Integer a, Integer b){
              return b - a;
          }
        };
        
       maxHeap = new PriorityQueue<>(nums.length, heapCmp);
       minHeap = new PriorityQueue<>(nums.length);
       totalNum = 0;
        
        for(int i = 0; i < nums.length; i++){
            res[i] = getMedian(nums[i]);
        }
        
        return res;
    }
    
    public int getMedian(int value){
        maxHeap.offer(value);
        totalNum++;
        if(totalNum%2 == 0){
            minHeap.offer(maxHeap.poll());
        }else{
            if(minHeap.isEmpty()){
                return maxHeap.peek();
            }
            if(maxHeap.peek() > minHeap.peek()){
                int max = maxHeap.poll();
                int min = minHeap.poll();
                maxHeap.offer(min);
                minHeap.offer(max);
            }
        }
        
        return maxHeap.peek();
    }
}
