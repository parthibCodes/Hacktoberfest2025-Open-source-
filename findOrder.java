
// User function Template for Java
class Solution {
    public String findOrder(String[] words) {
        // code here
        Map<Character,Set<Character>> graph = new HashMap<>();
        Map<Character,Integer> inDegree = new HashMap<>();
        for(String word : words){
            for(char c : word.toCharArray()){
                graph.putIfAbsent(c,new HashSet<>());
                inDegree.putIfAbsent(c,0);
            }
        }
        for(int i =0;i<words.length-1;i++){
            String w1= words[i];
            String w2= words[i+1];
            if(w1.length() > w2.length() && w1.startsWith(w2)){
                return "";
            }
            int len = Math.min(w1.length(),w2.length());
            for(int j=0;j<len;j++){
                char c1= w1.charAt(j);
                char c2= w2.charAt(j);
                if(c1 != c2){
                    if(!graph.get(c1).contains(c2)){
                        graph.get(c1).add(c2);
                        inDegree.put(c2,inDegree.get(c2)+1);
                    }
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for(char c: inDegree.keySet()){
            if(inDegree.get(c) == 0){
                queue.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            char current = queue.poll();
            sb.append(current);
            for(char neighbor : graph.get(current)){
                inDegree.put(neighbor,inDegree.get(neighbor)-1);
                if(inDegree.get(neighbor) == 0){
                    queue.offer(neighbor);
                }
            }
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
