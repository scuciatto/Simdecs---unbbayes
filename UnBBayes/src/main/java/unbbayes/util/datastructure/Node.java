package unbbayes.util.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	 
    public T data;
    public List<Node<T>> children;
    public Node<T> parent = null;
    
    /**
     * Default ctor.
     */
    public Node() {
        super();
    }
 
    /**
     * Convenience ctor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public Node(T data) {
        this();
        setData(data);
    }
    
    public void setParent(Node<T> p) {
    	parent = p;
    }
    
    public Node<T> getParent() {
    	return parent;
    }
    
     
    /**
     * Return the children of Node<T>. The Tree<T> is represented by a single
     * root Node<T> whose children are represented by a List<Node<T>>. Each of
     * these Node<T> elements in the List can have children. The getChildren()
     * method will return the children of a Node<T>.
     * @return the children of Node<T>
     */
    public List<Node<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
         
    public Node<T> getChild(int index){
    	return children.get(index);
    }
    
    /**
     * Adds a child to the list of children for this Node<T>. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a Node<T> object to set.
     */
    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        children.add(child);
        child.setParent(this);
    } 
	
    /**
     * Inserts a Node<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the Node<T> element at index index of the List<Node<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
 
    public void removeAll(){
    	if( children != null )
    		children.removeAll(children);
    }
     
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
     
  
    /*
    public void addChildrenAtLast( Node<T> source )
	{  
    	for( int i = 0; i < this.getNumberOfChildren(); i++ )
    	{
    		Node<T> child = children.get(i);  
    		child.assignChildren(source); 
    	}
	}
    
    public void assignChildrenWithSubchildren( Node<T> source )
	{ 
    	assignChildren( this, source );
    	
    	for( int i = 0; i < source.getNumberOfChildren(); i++ )
    	{
    		Node<T> childSource = source.getChild(i); 
    		Node<T> childNew = new Node<T>();
    		 
    		childNew.setData(childSource.getData());
    		 
    		children.get(i).addChild(childNew) ;
    	}
	}
    
    public void assignChildren( Node<T> source )
	{ 
    	assignChildren( this, source );
    	 
	}
    
	public void assignChildren( Node<T> target, Node<T> source )
	{ 
		 for(Node<T> e : source.getChildren()) 
		 {
			 Node<T> child = new Node<T>();
			 
			 child.setData(e.getData());
			 
			 target.addChild(child);
			 
			 assignChildren( child, e );
	     }
	}
	     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(getData().toString()).append(",[");
        int i = 0;
        for (Node<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();
    }
}
