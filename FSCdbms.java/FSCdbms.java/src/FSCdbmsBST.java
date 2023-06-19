/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class FSCdbmsBST {

    private FSCstudent root;

    public FSCdbmsBST() {
        root = null;
    }

    public FSCdbmsBST(FSCstudent root) {
        this.root = root;
    }


    public boolean isEmpty( ){
		return root == null;
	}

    //
	// boolean | search(int)
	//
    public boolean search(int data) {
		return search(root, data);
	}

    //
	// boolean | search(FSCstudent, int)
	//
	private boolean search(FSCstudent p, int data) {
		if (p == null)
			return false;
		else {
			// if the data we are searching for is found at p (at the current root)
			if (data == p.getID())
				return true;
			else if (data < p.getID())
				return search(p.getLeft(), data);
			else
				return search(p.getRight(), data);
		}
	}


	//
	// void | insert(FSCstudent)
	//
	public void insert(FSCstudent data) {
		FSCstudent newNode = new FSCstudent(data);
		root = insert(root, newNode);
	}
	//
	// FSCstudent | insert(FSCstudent, FSCstudent)
	//
	private FSCstudent insert(FSCstudent p, FSCstudent newNode) {
		// IF there is no tree, newNode will be the root, so just return it
		if (p == null)
			return newNode;
		
		// ELSE, we have a tree. Insert to the right or the left
		else {
			// Insert to the RIGHT of root
			if (newNode.getID() > p.getID()) {				
				// Recursively insert into the right subtree
				// The result of insertion is an updated root of the right subtree
				FSCstudent temp = insert(p.getRight(), newNode);
				// Save this newly updated root of right subtree into p.right
				p.setRight(temp);
			}
			// Insert to the LEFT of root
			else {				
				// Recursively insert into the left subtree
				// The result of insertion is an updated root of the left subtree
				FSCstudent temp = insert(p.getLeft(), newNode);
				// Save this newly updated root of left subtree into p.left
				p.setLeft(temp);
			}
		}
		// Return root of updated tree
		return p;
	}
	

	//
	// void | inorder()
	//
	public void inorder() {
		System.out.println("\tAll records saved in FSCdbms:");
        System.out.println("\tSTUDENT ID     NAME                     AGE     YEAR/LEVEL     GPA");
		inorder(root);
	}
	//
	// void | inorder(FSCstudent)
	//
	private void inorder(FSCstudent p) {
		if (p != null) {
			inorder(p.getLeft());
			//System.out.print(p.getID() + ", ");
			if (p.getCourses().isEmpty()) {
				System.out.printf("\t%-15d%-25s%-8s%-14s  N/A\n", p.getID(), p.getFullName(), p.getAge(), p.getLevelString());
			}
			else {
				System.out.printf("\t%-15d%-25s%-8s%-14s%5.2f\n", p.getID(), p.getFullName(), p.getAge(), p.getLevelString(), p.getCourses().calculateGPA());
			}
			inorder(p.getRight());
		}
	}
	
	


	
    //
    // FSCstudent | minNode(FSCstudent)
    //
    public FSCstudent minNode(FSCstudent root) {
        if (root == null) {
            return null;
        }
        else {
            if (root.getLeft() == null) {
                return root;
            }
            else {
                return minNode(root.getLeft());
            }
        }
    }
    
	
    //
    // FSCstudent | maxNode(FSCstudent)
    //
    public FSCstudent maxNode(FSCstudent root) {
        if (root == null) {
            return null;
        }
        else {
            if (root.getRight() == null) {
                return root;
            }
            else {
                return maxNode(root.getRight());
            }
        }
    }
	

	//
	// FSCstudent | findNode(FSCstudent)
	//
	public FSCstudent findNode(FSCstudent data) {
		return findNode(root, data);
	}
	//
	// FSCstudent | findNode(FSCstudent, FSCstudent)
	//
	private FSCstudent findNode(FSCstudent p, FSCstudent data) {
		if (p == null)
			return null;
		else {
			// if the data we are searching for is found at p (at the current root)
			if (data.getID() == p.getID())
				return p;
			else if (data.getID() < p.getID())
				return findNode(p.getLeft(), data);
			else
				return findNode(p.getRight(), data);
		}
	}

	//
	// FSCstudent | findNode(int)
	//
	public FSCstudent findNodeByID(int id) {
		return findNodeByID(root, id);
	}
	//
	// FSCstudent | findNode(FSCstudent, int)
	//
	private FSCstudent findNodeByID(FSCstudent p, int id) {
		if (p == null)
			return null;
		else {
			// if the data we are searching for is found at p (at the current root)
			if (id == p.getID())
				return p;
			else if (id < p.getID())
				return findNodeByID(p.getLeft(), id);
			else
				return findNodeByID(p.getRight(), id);
		}
	}

	//
	// FSCstudent | findNodeByName(String)
	//
	public FSCstudent findNodeByName(String name) {
		return findNodeByName(root, name);
	}
	//
	// FSCstudent | findNodeByName(FSCstudent, int)
	//
	private FSCstudent findNodeByName(FSCstudent p, String name) {
		if (p == null)
			return null;
		else {
			// if the data we are searching for is found at p (at the current root)
			if (name.compareToIgnoreCase(p.getLastName() + " " + p.getFirstName()) == 0)
				return p;
			else if (name.compareToIgnoreCase(p.getLastName() + " " + p.getFirstName()) < 0)
				return findNodeByName(p.getLeft(), name);
			else
				return findNodeByName(p.getRight(), name);
		}
	}
	
	//
	// FSCstudent | parent(FSCstudent)
	//
	public FSCstudent parent(FSCstudent p) {
		return parent(root, p);
	}
	//
	// FSCstudent | parent(FSCstudent, FSCstudent)
	//
	private FSCstudent parent(FSCstudent root, FSCstudent p) {
		// Take care of NULL cases
		if (root == null || root == p)
			return null; // because there is on parent
		
		// If root is the actual parent of node p
		if (root.getLeft()==p || root.getRight()==p)
			return root; // because root is the parent of p
		
		// Look for p's parent in the left side of root
		if (p.getID() < root.getID())
			return parent(root.getLeft(), p);
		
		// Look for p's parent in the right side of root
		else if (p.getID() > root.getID())
			return parent(root.getRight(), p);
		
		// Take care of any other cases
		else
			return null;
	}
	
	
	//
	// boolean | isLeaf(FSCstudent)
	//
	public boolean isLeaf(FSCstudent p) {
		return (p.getLeft()==null && p.getRight()==null);
	}
	
	
	//
	// boolean | hasOnlyLeftChild(FSCstudent)
	//
	public boolean hasOnlyLeftChild(FSCstudent p) {
		return (p.getLeft()!=null && p.getRight()==null);
	}
	
	
	//
	// boolean | hasOnlyRightChild(FSCstudent)
	//
	public boolean hasOnlyRightChild(FSCstudent p) {
		return (p.getLeft()==null && p.getRight()!=null);
	}
	
	
	//
	// void | delete(FSCstudent)
	//
	public void delete(FSCstudent data) {
		root = delete(root, data);
	}
	//
	// FSCstudent | delete(FSCstudent, int)
	//
	private FSCstudent delete(FSCstudent p, FSCstudent data) {
		FSCstudent node2delete, newnode2delete, parent;
		FSCstudent saveValue;
		
		// Step 1: Find the node we want to delete
		node2delete = findNode(data);
		
		// If node is not found (does not exist in tree), we clearly cannot delete it.
		if (node2delete == null)
			return root;
		
		// Step 2: Find the parent of the node we want to delete
		parent = parent(p, node2delete);
		
		// Step 3: Perform Deletion based on three possibilities
		
		// **1** :  node2delete is a leaf node
		if (isLeaf(node2delete)) {
			// if parent is null, this means that node2delete is the ONLY node in the tree
			if (parent == null)
				return null; // we return null as the updated root of the tree
			
			// Delete node if it is a left child
			if (data.getID() < parent.getID())
				parent.setLeft(null);
			// Delete node if it is a right child
			else
				parent.setRight(null);
			
			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}
		
		// **2** : node2delete has only a left child
		if (hasOnlyLeftChild(node2delete)) {
			// if node2delete is the root
			if (parent == null)
				return node2delete.getLeft();
			
			// If node2delete is not the root,
			// it must the left OR the right child of some node
			
			// IF it is the left child of some node
			if (data.getID() < parent.getID())
				parent.setLeft(parent.getLeft().getLeft());
			// ELSE it is the right child of some node
			else
				parent.setRight(parent.getRight().getLeft());
			
			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}
		
		// **3** :  node2delete has only a right child
		if (hasOnlyRightChild(node2delete)) {
			// if node2delete is the root
			if (parent == null)
				return node2delete.getRight();
			
			// If node2delete is not the root,
			// it must the left OR the right child of some node
			
			// IF it is the left child of some node
			if (data.getID() < parent.getID())
				parent.setLeft(parent.getLeft().getRight());
			// ELSE it is the right child of some node
			else
				parent.setRight(parent.getRight().getRight());
			
			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}
		
		// **4** :  node2delete has TWO children.
		// Remember, we have two choices: the minVal from the right subtree (of the deleted node)
		// or the maxVal from the left subtree (of the deleted node)
		// We choose to find the minVal from the right subtree.
		newnode2delete = minNode(node2delete.getRight());

		// Now we need to temporarily save the data value(s) from this node
		
		saveValue = newnode2delete.getData();

		// Now, we recursively call our delete method to actually delete this node that we just copied the data from
		p = delete(p, saveValue);
		
		// Now, put the saved data (in saveValue) into the correct place (the original node we wanted to delete)
		node2delete.setData(saveValue);
		
		// Finally, return the root of the tree (in case the root got updated)
		return p;
	}
    
}
