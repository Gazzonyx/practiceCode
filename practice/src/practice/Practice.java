package practice;

/**
 *
 * @author scottl
 */
public class Practice {
    private static final int arraySize = 3;
    
    private class MyVector<T> 
    {
     private Object[] items = null;
     
     public MyVector(T item)
     {
         items = new Object[1];
         items[0] = item;
     }
     
     public MyVector()
     {
         items = new Object[0];
     }
     
     public T getItem(int index) throws ArrayIndexOutOfBoundsException
     {
         if (index > items.length - 1 || index < 0)
             throw new ArrayIndexOutOfBoundsException(index);
         return (T)items[index];
     }
     
     public T removeItem(int index) throws ArrayIndexOutOfBoundsException
     {
         Object item = getItem(index);
         
         for (int ct = index + 1; ct < items.length; ct++)
             items[ct - 1] = items[ct];
         shrinkArray();
         
         return (T)item;
     }
     
     public T getTail() throws ArrayIndexOutOfBoundsException
     {
         return getItem(items.length - 1);
     }
     
     public T getHead() throws ArrayIndexOutOfBoundsException
     {
         return getItem(0);
     }
     
     public void putItem(T item)
     {
         extendArray();
         items[items.length - 1] = item; 
     }
     
     public void printArray()
     {
         for (int ct = 0; ct < items.length; ct++)
             System.out.println("[" + ct + "]" + items[ct]);
         println();
     }
     
     private void shrinkArray()
     {
         Object[] newArray = new Object[items.length - 1];
         for (int ct = 0; ct < newArray.length; ct++)
             newArray[ct] = items[ct];
         items = newArray;
     }
     
     private void extendArray()
     {
         Object[] newArray = new Object[items.length + 1];
         for (int ct = 0; ct < items.length; ct++)
             newArray[ct] = items[ct];
         items = newArray;
     }
    }
    
    
    private abstract class TreeNode<T extends Comparable>
    {
        protected T item;
        protected TreeNode<T> parent;
        
        
        public TreeNode()
        {
         item = null;
         parent = null;
        }
        
        public TreeNode(T item)
        {
            this();
            this.item = item;
        }
        
        public TreeNode(T item, TreeNode parent)
        {
            this(item);
            this.parent = parent;
        }
        
        
        public void setParent(TreeNode parentNode)
        {parent = parentNode;}
    }
    
    private abstract class Tree<T extends Comparable>
    {
        protected TreeNode root;
        
//        public abstract TreeNode insertItem(T item, TreeNode parent);
    }
    
    
    
    private class BinaryTreeNode<T extends Comparable> extends TreeNode
    {
        protected BinaryTreeNode left = null;
        protected BinaryTreeNode right = null;
        
        
        public BinaryTreeNode(T item)
        {super(item);}
        
        
        public void setLeft(BinaryTreeNode leftChild)
        {left = leftChild;}
        
        public void setRight(BinaryTreeNode rightChild)
        {right = rightChild;}
    }
    
    private class BinarySearchTree<T extends Comparable> extends Tree
    {
        public BinarySearchTree(T item)
        {root = new BinaryTreeNode(item);}
        
        
        public void insertItem(T item)
        {insertItem(item, (BinaryTreeNode)root);}
        
        private BinaryTreeNode insertItem(T item, BinaryTreeNode localRoot)
        {
            if (localRoot == null)
                localRoot = new BinaryTreeNode(item);
            
            else if (item.compareTo((T)localRoot.item) <= 0)
            {
                localRoot.left = insertItem(item, localRoot.left);
                localRoot.left.setParent(localRoot);
            }
            
            else if (item.compareTo((T)localRoot.item) > 0)
            {
                localRoot.right = insertItem(item, localRoot.right);
                localRoot.right.setParent(localRoot);
            }
            
            return localRoot;
        }
    }
    
    
    
    
    
    
    
    private class MyTree<T extends Comparable<T>>
    {   
        private MyNode<T> treeRoot;
        
        private class MyNode<T>
        {
            T item;
            MyNode<T> left;
            MyNode<T> right;
            
            public MyNode()
            {}
            
            public MyNode(T item)
            {this.item = item;}
        }
        
        public MyTree(T item)
        {
            treeRoot = new MyNode();
            treeRoot.item = item;
        }
        
        public MyTree()
        {treeRoot = null;}
        
        
        public void insertItem(T item)
        {insertItem(item, treeRoot);}
        
        public void printTree()
        {printTree(treeRoot);}
        
        public void printTree(MyNode node)
        {
            if (node == null)
                return;
            
            System.out.println("Node: " + node.item);
            if (node.left != null)
            {
                System.out.println("Left: ");
                printTree(node.left);
            }
            if (node.right != null)
            {
                System.out.println("Right: ");
                printTree(node.right);
            }
        }
        
        public boolean contains(T item)
        {return contains(item, treeRoot);}
        
        private boolean contains(T item, MyNode node)
        {
            if (node == null)
                return false;
            
            boolean found = false;
            if (node.item.equals(item))
                found = true;
            
            else if (item.compareTo((T)node.item) <= 0)
                found = contains(item, node.left);
            else if (item.compareTo((T)node.item) > 0)
                found = contains(item, node.right);
            
            return found;
        }
        
        private MyNode<T> insertItem(T item, MyNode localRoot)
        {
            if (localRoot == null)
                localRoot = new MyNode(item);
            
            else if (item.compareTo((T)localRoot.item) <= 0)
                localRoot.left = insertItem(item, localRoot.left);
            
            else if (item.compareTo((T)localRoot.item) > 0)
                localRoot.right = insertItem(item, localRoot.right);
            
            return localRoot;
        }
        
        
    }
    
    
    public Practice()
    {
        
        MyTree<Integer> tree = new MyTree(10);
        tree.insertItem(5);
        tree.insertItem(15);
        tree.insertItem(14);
        tree.insertItem(16);
        tree.printTree();
        
        int find = 16;
        System.out.println("Contains " + find + " : " + tree.contains(find));
        
/*        
        MyVector<Integer> myVector = new MyVector();
        
        for (int ct = 0; ct < 5; ct++)
            myVector.putItem(ct);
        myVector.printArray();
        
        myVector.removeItem(4);
        myVector.printArray();
*/
        
/*      
        int[] array = new int[arraySize];
        
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        
        printArray(array);
        println();
        
        for (int ct = 0; ct < arraySize; ct++)
        {
            array = shrinkArray(array);
            printArray(array);
            println();
        }
*/
    }

    
    public int[] extendArray(int[] incoming)
        {
         int[] outgoing = new int[incoming.length + 1];
         for (int i = 0; i<incoming.length; i++)
             outgoing[i] = incoming[i];
         
         return outgoing;
        }
    
    public int[] shrinkArray(int[] incoming)
    {
        int[] outgoing = new int[incoming.length - 1];
        for (int i = 0; i < outgoing.length; i++)
            outgoing[i] = incoming[i];
        
        return outgoing;
    }
    
    
    public void printArray(int[] incoming)
    {
        for(int ct = 0; ct < incoming.length; ct++)
            System.out.println("[" + ct + "]" + incoming[ct]);
    }
    
    public static void println()
    {
        System.out.println("-------------------------------------------------");
    }
    
    
    
    
    public static void main(String[] args) 
    {
        Practice practice = new Practice();
        System.exit(0);
    }
}