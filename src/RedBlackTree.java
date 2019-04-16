
public class RedBlackTree {
	private RedBlackNode root;
	private RedBlackNode nil;
	private int size;
	private int compares;
	
public RedBlackTree(){
		
	/**
	 * Construct an empty RedBlackTree with a black node.
	 *  Big Theta = Θ(1)
	 * 
	 */
	nil = new RedBlackNode("-1", 0, null, null, null);
	root = nil;
	size = 0;
	compares = 0;
		
	}

	/**
	 * 
	 *  @return number of values inserted into the tree.
	 *   Big Theta = Θ(1)
	 * 
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 *  Perfrom an inorder traversal of the tree.
	 *   The inOrderTraversal(RedBlackNode) method is recursive and displays 
	 *   the content of the tree. It makes calls on System.out.println().
	 *   This method would normally be private.
	 *   @param t - the root of the tree on the first call.
	 *   Big Theta = Θ(n)
	 * 
	 */
	public void inOrderTraversal(RedBlackNode t) {
		if(t.getLc() != nil) {
			inOrderTraversal(t.getLc());
		}
		
		System.out.println(t.toString()+" ");
		
		if(t.getRc() != nil) {
			inOrderTraversal(t.getRc());
		}
		
	}
	/**
	 *   The no argument inOrderTraversal() method calls 
	 *   the recursive inOrderTraversal(RedBlackNode) - passing the root.
	 * 
	 */
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}
	
	/**
	 *  Perform a reverseOrder traversal of the tree. 
	 *  The reverseOrderTraversal(RedBlackNode) method is recursive 
	 *  and displays the content of the tree in reverse order. 
	 *  This method would normally be private.
	 *  @param t - the root of the tree on the first call.
	 *   Big Theta = Θ(n)
	 * 
	 */
	public void reverseOrderTraversal(RedBlackNode t) {
		
		if (t.getRc() != nil) {
            reverseOrderTraversal(t.getRc());
        }
        if (t.getLc() != nil) {
            reverseOrderTraversal(t.getLc());
        }
        System.out.println(t.toString()+" ");
	}
	
	/**
	 *   The no argument reverseOrderTraversal() method calls the recursive 
	 *   reverseOrderTraversal(RedBlackNode) - passing the root.
	 *   
	 * 
	 */
	public void reverseOrderTraversal() {
		reverseOrderTraversal(root);
	}
	
	/**
	 * The insert() method inserts an item into the red black tree.
	 * 
	 * @param value - is an integer to be inserted
	 * @precondition memory is available for insertion
	 * Big Theta = Θ(log(n))
	 */
	public void insert(java.lang.String value) {
		RedBlackNode y = nil;
        RedBlackNode x = root;
        RedBlackNode z = new RedBlackNode(value, 1, null, null, null);
        while(x != nil) {
        		y = x;
        		if(z.getData().compareTo(x.getData()) < 0) {
        			x = x.getLc();
        		}
        		else {
        			x = x.getRc();
        		}
        }
        z.setP(y);
        if( y == nil) {
        		root = z;
        }
        else {
        		if(z.getData().compareTo(y.getData()) < 0) {
        			y.setLc(z);
        		}
        		else {
        			y.setRc(z);
        		}
        }
        
        z.setLc(nil);
        z.setRc(nil);
        z.setColor(1);
        RBInsertFixup(z);
       
        size++;
        
	}
	
	/**
	 * The boolean contains() returns true if the String v is in the RedBlackTree 
	 * and false otherwise. It counts each comparison it makes in the variable 
	 * compares.
	 * @param  x - the value to search for
	 * @returns true if v is in the tree, false otherwise;
	 *
	 * Big Theta = Θ(log(n))
	 */
	public boolean contains(java.lang.String v) {
		compares = 0;
		RedBlackNode target = root;
		while (target != nil) {
			compares++;
			if(target.getData().equals(v)) {
				return true;
			}
			else if (target.getData().compareTo(v) < 0) {
				target = target.getRc();
			}
			else if (target.getData().compareTo(v) > 0) {
				target = target.getLc();
			}
		}
		return false;
	}
	
	/**
	 * @returns number of comparisons made in last call on the contains method.
	 *
	 * Big Theta = Θ(1)
	 */
	public int getRecentCompares() {
		return compares;
	}
	
	/**
	 * 
	 * The method closeBy(v) returns a value close to v in the tree. 
	 * If v is found in the tree it returns v.
	 * @param  v - the value to search close by for.
	 *
	 * Big Theta = Θ(log(n))
	 */
	public java.lang.String closeBy(java.lang.String v){
		RedBlackNode target = root;
		RedBlackNode prev = target;
		
		while (target != nil) {
			prev = target;
			if (target.getData().equals(v))
				return v;
			else if (target.getData().compareTo(v) < 0) {
				target = target.getRc();
			}
			else if (target.getData().compareTo(v) > 0) {
				target = target.getLc();
			}
		}
		return prev.getData();
	}
	
	/**
	 * 
	 * This a recursive routine that is used to compute the height of the red black tree. 
	 * It is called by the height() method. 
	 * The height() method passes the root of the tree to this method. 
	 * This method would normally be private.
	 *@param t - a pointer to a node in the tree.
	 *@returns the height of node t
	 *
	 * Big Theta = Θ(log(n))
	 */
	public int height(RedBlackNode t) {
		if(t == nil)
			return -1;
		return Math.max(height(t.getLc())+1, height(t.getRc())+1);
	}
	
	/**
	 * 
	 * This method calls the recursive method.
	 * 
	 *@returns the height of node t
	 * 
	 */
	public int height() {
		return height(root);
	}
	
	/**
	 *   This method displays the RedBlackTree in level order. It first displays the root. Then it displays all children of the root. 
	 *   Then it displays all nodes on level 3 and so on. It is not recursive. It uses a queue.
	 *   
	 *   Big Theta = Θ(n)
	 * 
	 */
	public void levelOrderTraversal() {
		RedBlackNode x;
		Queue a = new Queue();
		a.enQueue(root);
		while(!a.isEmpty()) {
			x = (RedBlackNode) a.deQueue();
			System.out.println(x.toString()+" ");
			if(x.getLc()!=nil)
				a.enQueue(x.getLc());
			
			if(x.getRc()!=nil)
				a.enQueue(x.getRc());
		}
		
		
	}
	/**
	 *   leftRotate() performs a single left rotation. 
	 *   This would normally be a private method. 
	 *   It executes the following algorithm from CLR.
	 *  
	 *   Big Theta = Θ(1)
	 * 
	 */
	public void leftRotate(RedBlackNode x) {
		  RedBlackNode y = x.getRc();
		  x.setRc(y.getLc());
		  y.getLc().setP(x);
		  y.setP(x.getP());
		  
		  if(x.getP() == nil) {
			  root = y;
		  }
		  else {
			  if(x == x.getP().getLc()) {
				  x.getP().setLc(y);
			  }
			  else {
				  x.getP().setRc(y);
			  }
		  }
		  y.setLc(x);
		  x.setP(y);
		
	}
	/**
	 *   rightRotate() performs a single right rotation 
	 *   This would normally be a private method. 
	 *   It executes the following algorithm from CLR.
	 *  
	 *   Big Theta = Θ(1)
	 * 
	 */
	public void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		
		if(x.getP() == nil) {
			root = y;
		}
		else {
			if( x == x.getP().getLc()) {
				x.getP().setLc(y);
			}
			else {
				x.getP().setRc(y);
			}
		}
		
		y.setRc(x);
		x.setP(y);
	}
	/**
	 *   Fixing up the tree so that Red Black Properties are preserved.
	 *   This would normally be a private method.
	 *   
	 *   Big Theta = Θ(log n)
	 * 
	 */
	public void RBInsertFixup(RedBlackNode z) {
		RedBlackNode y;
		while(z.getP().getColor() == 1) {
			//z's father is left chindren of his grandpa
			if(z.getP()==z.getP().getP().getLc()){
				y = z.getP().getP().getRc();
				if(y.getColor() == 1) {
					z.getP().setColor(0);
					y.setColor(0);
					z.getP().getP().setColor(1);
					z = z.getP().getP();
				}
				else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						leftRotate(z);
					}
					z.getP().setColor(0);
					z.getP().getP().setColor(1);
					rightRotate(z.getP().getP());
				}
			}
			else {
				y = z.getP().getP().getLc();
				if(y.getColor() == 1) {
					z.getP().setColor(0);
					y.setColor(0);
					z.getP().getP().setColor(1);
					z = z.getP().getP();
				}
				else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(0);
					z.getP().getP().setColor(1);
					leftRotate(z.getP().getP());
				}
			}
		}
		root.setColor(0);
	}


	/**
	 *   Test the RedBlack tree.
	 * 
	 */
	 public static void main(String[] args) {

	        RedBlackTree rbt = new RedBlackTree();

	        for(int j = 1; j <= 5; j++) rbt.insert(""+j);
	        
	        // after 1..5 are inserted
	        System.out.println("RBT in order");
	        rbt.inOrderTraversal();
	        System.out.println("RBT level order");
	        rbt.levelOrderTraversal();
	        
	       // is 3 in the tree
	        
	        if(rbt.contains(""+3)) System.out.println("Found 3");
	        else System.out.println("No 3 found"); 
	        
	        // display the height
	        System.out.println("The height is " + rbt.height());   
	        
	    }
	 
}
