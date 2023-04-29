package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import misc.Graph_1.Node;

public class Binary_tree {

	   public static class Node{
	    	public int _id;
	    	public ArrayList<Node> adj;
	    	public int marked = 0;
	    	public int value;
	    	//
	    	public int sum = 0;

	    	
	    	public Node(int id,int val) {
	    		_id = id;
	    		value = val;
	    		adj = new ArrayList<>();
	    		adj.add(0,null);
	    		adj.add(1,null);
	    		adj.add(2,null);
	    	
	    	}
	    	
	    	/*public String toString() {
	    		return "{id:" + _id + " / value:" + value + "}";
	    	}*/
	    	
	    	public String toString() {
	    		return ""+value;
	    	}
	    	
	    	public void set_value(int v) {
	    		value = v;
	    	}
	    	
		    public Node left() {
		    	if(this.adj.get(0) != null) {
		    		return this.adj.get(0);
		    	}
		    	return null;
		    }
		    public Node right() {
		    	if(this.adj.get(1) != null) {
		    		return this.adj.get(1);
		    	}
		    	return null;
		    }
		    
		    public Node get_parent(){
		    	if(this.adj.get(2) != null){
		    		return this.adj.get(2);
		    	}
		    	return null;
		    }
		    
	    	
	    }
	    
	    public ArrayList<Node> nodes;
	    
	    //
	    
	    
	    
	    public Binary_tree() {
	    	nodes = new ArrayList<>();
	    }
	    
	    public void add(int i,int val) {
	    	Node novo = new Node(i,val);
	    	nodes.add(novo);

	    }
	    
	    public void link_left(int id1,int id2){
	    	Node n = nodes.get(id1);
	    	n.adj.remove(0);
	    	n.adj.add(0, nodes.get(id2));
	        Node r = nodes.get(id2);
	        if(r.adj.get(2) == null) {
	        	r.adj.remove(2);
	        	r.adj.add(2,n);
	        }
	        
	    }
	    
	    public void link_right(int id1,int id2) {
	    	Node n = nodes.get(id1);
	    	n.adj.remove(1);
	    	n.adj.add(1,nodes.get(id2));
	    	Node r = nodes.get(id2);
	    	if(r.adj.get(2) == null) {
	    		r.adj.remove(2);
	    		r.adj.add(2,n);
	    	}
	    	

	    }
	    
	    
	    public Node get_node(int i) {
	    	return nodes.get(i);
	    	
	    }
	    

	    public ArrayList<Node> get_nodes(){
	    	return nodes;
	    }
	    
	    /*Find node*/ 	
	    
	    //I have a node n that come from a different tree and i want check if n is on the 2 trees
	    public Node find(Node n){  	 
	    	for(int i = 0;i < nodes.size();i++) {
	    		if(nodes.get(i) == n) {
	    			return nodes.get(i);
	    		}
	    	}
	    	
	    	
	    	return null;
	    }
	    
	    /*Delete node*/
	    
	    //If i delete a node i have to delete its subtree 
	    
	    public void remove_node(Node n){
	    	
	    	
	    	Node parent = n.get_parent();
	    	
	    	if(parent.left() == n) {
	    		parent.adj.set(0, null);
	    	}
	    	else if(parent.right() == n){
	    	    parent.adj.set(1, null);
	    	}
	    	
	    	
	    	nodes.remove(n);
	    	
	    	
	    }
	    
	    public void delete(Node n) {
	    	
   	
	    	if(n.left() == null && n.right() == null){
	    		this.remove_node(n);
	    		return;
	    	}
	    	
	    	else {
	    		if(n.left() != null) {
	    			delete(n.left());
	    			this.remove_node(n);
	    		}
	    		if(n.right() != null) {
	    			delete(n.right());
	    			this.remove_node(n);
	    		}
	    		
	    	}	    
	    		    	
	    }
	    
	    
	    
	  /*Given a number k ,determine the number of paths that can be done that sums up to k*/
	    
	    /*Solution 2*/
	    
	    
	    public int sub_k(LinkedList<Integer> l,int k){
	    	
	    	HashMap<Integer,Integer> h = new HashMap<>();
	    	
	    	
	    	for(int i = 0;i < l.size();i++){
	    		int x = l.get(i);
	    		if(h.containsKey(x)){
	    			h.put(x, h.get(x)+1);
	    		}
	    		else {
	    			h.put(x, 1);
	    		}
	    	}
	    	
	    	int c = 0;
	    	
	    	for(int i = 1;i < l.size();i++){
	    		int x = l.get(i-1);
	    		//int y = h.get(x);
	    		if(h.containsKey(x+k)){
	    			c += h.get(x+k);
	    		}
	    	}
	    	if(h.containsKey(k)) {
	    		c += h.get(k);
	    	}
	    	return c;
	    	
	    	
	    }
	    
	    
	    public int count_paths(Node n,int a,int k,LinkedList<Integer> l){
	    	
	    	int num = 0;
	    	
	    	n.sum = a+n.value;
    		if(l.isEmpty()){
    			Node r = n.get_parent();
    			while(r != null){
    				l.addFirst(r.sum);
    				r = r.get_parent();
    			}
    		}
    		l.add(n.sum);
	    	
	    	
	    	if(n.left() == null & n.right() == null){
	    		//l.add(a+n.value);
	    		num += sub_k(l,k);
	    		System.out.println(l);
	    		l.clear();
	    	    return num;
	    	}
	    	else {
	    		
	    		/*n.sum = a+n.value;
	    		if(l.isEmpty()){
	    			Node r = n.get_parent();
	    			while(r != null){
	    				l.addFirst(r.sum);
	    				r = r.get_parent();
	    			}
	    		}
	    		l.add(n.sum);*/
	    		
	    		if(n.left() != null) {
	    			num += count_paths(n.left(),n.sum,k,l);
	    		}
	    		if(n.right() != null) {
	    			num += count_paths(n.right(),n.sum,k,l);
	    		}
	    		
	    		
	    	}
	    	return num;
	    	
	    }
	    
	   
	   
	    
	  
	   
	  
	
		  
	  
	 
	   
	   }
	    

	    
	   	

