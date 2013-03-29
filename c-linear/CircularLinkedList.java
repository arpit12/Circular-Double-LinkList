class Node {
 
    Object data;
    Node next;
 
    public Node( Object data ) { //circular hai issleye null ni kar sakta warna 1 hi constructor banate, self  loop dalna hai
        this.data = data;
    }
 
    public Node( Object data, Node next ) {
        this.data = data;
        this.next = next;
    }
}
 
public class CircularLinkedList {
 
    Node entryNode;
 
    /**
     * Adds a node to the circular linked-list via the entry node.
     */
    public void add( Object data ) {
 
        if( entryNode == null ) {
 
            // Initialize the entry node.
            //
            entryNode = new Node( data );  // cant be null bec. self loop has to assigned.
 
            // Link entry node to self, as there is only one node on the list.
              entryNode.next = entryNode; // note apply it as it is circular. 
			
			/// Note- dont do this. we use this for forward traversal.
           ///   entryNode = entryNode.next;
 
        } else {
 
            // Add the node to the circular linked-list.
            
            entryNode.next = new Node( data, entryNode.next );
        }
    }
 
    /**
     * Removes the first occurrence of the node (as specified by the data).
     */
    public void remove( Object data ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode; 
 
            do {
 
                if( itr.next.data.equals( data ) ) 
				{  //current head(data) in loop equals or data(we gave ) then remove it
 
                    if( itr.next == entryNode && entryNode != entryNode.next )
					{
 
                        // There are still nodes left in the list, but the node 
                        // that we need to remove is the entryNode(first node); so we need
                        // to reassign the field to the next node in line.
                        
                        itr.next = itr.next.next;
                        entryNode = entryNode.next;
 
                    } else if( itr.next == itr.next.next ) {
 
                        // This condition will only be true if there is only one 
                        // node left in the list, which will be the entryNode.
                        //
                        entryNode = null;
 
                    } else {
 
                        // The node that we need to remove is a regular node, 
                        // i.e. not an entryNode.
                        //
                        itr.next = itr.next.next;                            
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
     * Does a full iteration starting and ending at the entry node.
     */
    public void iterate( ) {
 
        if( entryNode != null ) {
 
            Node itr = entryNode;
 
            do {
 
                System.out.print( itr.data + " " );
                itr = itr.next;
 
            } while( itr != entryNode );  // as circular so 1=last means complete traversals
 
            System.out.println( );  // just for next line..
        }
    }
 
    /**
     * Test Method
     */
    public static void main( String[ ] args ) {
 
        // Initialize and populate the circular linked-list
        //
        CircularLinkedList list = new CircularLinkedList( );
 
        for( int i = 1; i <= 5; i++ ) {
            list.add( i );
        }
 
        // Prints: 5 4 3 2 1
        //
        list.iterate( );
 
        // Remove nodes 3, 2, 1 from the circular list.
        //
        for( int i = 1; i <= 3; i++ ) {
            list.remove( list.get( i ).data );
        }
 
        // Prints: 5 4
        //
        list.iterate( );
 
        // Get and remove node 4
        //
        Node node4 = list.get( 4 );
        list.remove( node4.data );      // it can also be done through  list.remove(4);
 
        // Prints: 5
        //
        list.iterate( );
		
        Node node5 = list.get( 5 );
        list.remove( node5.data );         list.iterate( );
    }
}