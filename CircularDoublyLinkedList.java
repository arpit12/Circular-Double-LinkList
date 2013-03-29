class Node {
 
    Object data;
    Node prev;
    Node next;
 
    public Node( Object data ) {
        this.data = data;
    }
 
    public Node( Object data, Node prev, Node next ) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
 
public class CircularDoublyLinkedList {
 
    Node entryNode;
 
    /**
     * Adds a node to the circular linked-list via the entry node.
     */
    public void add( Object data ) {
 
        if( entryNode == null ) {
 
            // Initialize the entry node.
            //
            entryNode = new Node( data );
 
            // Link entry node to self, as there is only one node on the list.
            //
            entryNode.prev = entryNode;
            entryNode.next = entryNode;
 
        } else {
 
            // Add the node to the circular linked-list.
            //
            entryNode.next = new Node( data, entryNode, entryNode.next );
 
            // Link the newly added node with the rest of the nodes in the list.
            //
            if( entryNode.prev == entryNode ) {
 
                // There is only one node on the list (i.e. entryNode) and
                // now we're adding the new node.
                //
                entryNode.prev = entryNode.next;
 
            } else {
 
                // There are at least two nodes on the list.
                //
                entryNode.next.next.prev = entryNode.next;
            }
        }
    }
 
    /**
     * Removes the first occurrence of the node (as specified by the data).
     */
    public void remove( Object data ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode; 
 
            do {
                if( itr.data.equals( data ) ) {
 
                    // Remove the links from the previous and next nodes.
                    //
                    itr.prev.next = itr.next;
                    itr.next.prev = itr.prev;
 
                    // Reassign the entryNode if necessary.
                    //
                    if( itr.next == itr ) {
 
                        // There's only one node left in the list, so we nullify entryNode.
                        //
                        entryNode = null;
 
                    } else if( itr == entryNode ) {
 
                        // Node referenced by entryNode has been removed, so we need to reassign
                        // the entryNode to the next available node.
                        //
                        entryNode = itr.next;
                    }
 
                    // Done!
                    //
                    return;
                }
 
                // Move to the next available node in the list.
                //
                itr = itr.next;
 
            } while( itr != entryNode );
        }
    }
 
    /**
     * Returns the first occurrence of the node that matches the specified 
     * data or null if not found. Has a O(n) run-time complexity.
     */
    public Node get( Object data ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode; 
 
            do {
 
                if( itr.data.equals( data ) ) {
                    return itr;
                }
 
                itr = itr.next;
 
            } while( itr != entryNode );
        }
 
        return null;
    }
 
    /**
     * Does a full forward iteration starting and ending at the entry node.
     */
    public void iterateForwards( ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode;
 
            do {
 
                System.out.print( itr.data + " " );
                itr = itr.next;
 
            } while( itr != entryNode );
 
            System.out.println( );
        }
    }
 
    /**
     * Does a full backwards iteration starting and ending at the entry node.
     */
    public void iterateBackwards( ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode;
 
            do {
 
                System.out.print( itr.data + " " );
                itr = itr.prev;
 
            } while( itr != entryNode );
 
            System.out.println( );
        }
    }
 
    /**
     * Test Method
     */
    public static void main( String[ ] args ) {
 
        // Initialize and populate the circular doubly-linked list
        //
        CircularDoublyLinkedList list = new CircularDoublyLinkedList( );
 
        for( int i = 1; i <= 6; i++ ) {
            list.add( i );
        }
 
        // Prints: 1 6 5 4 3 2 
        //
        list.iterateForwards( );
 
        // Prints: 1 2 3 4 5 6
        //
        list.iterateBackwards( );
	
 
        // Remove nodes 3, 2, 1 from the circular list.
        //
        for( int i = 1; i <= 3; i++ ) {
            list.remove( list.get( i ).data );
        }
		
			
		list.add(2);
        list.iterateBackwards( );
 
        // Prints: 6 5 4
        //
        list.iterateForwards( );
    }
}