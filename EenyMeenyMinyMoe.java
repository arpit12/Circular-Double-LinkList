public class EenyMeenyMinyMoe {
 
    public static final String[ ] countingRhyme = { "Eeny", "meeny", "miny",
            "moe", "catch", "the", "tiger", "by", "the", "toe", "if", "it",
            "hollers", "let", "him", "go", "eeny", "meeny", "miny", "moe",
            "you", "are", "it!" };
 
    public static final String[ ] names = { "Alex", "Bill", "Chris", "Debbie",
            "Jason", "Jeniffer", "Joanne", "Robert", "Sarah", "Scott" };
 
    public static void main( final String[ ] args ) {
 
        // Initialize the list.
        //
        final CircularDoublyLinkedList list = new CircularDoublyLinkedList( );
 
        // Populate the list
        //
        for( String name : names ) {
            list.add( name );
        }
 
        // Start counting and eliminating.
        //
        Node cursor = list.entryNode;
        while( true ) {
 
            for( final String word : countingRhyme ) {
                System.out.print( word + " " );
                cursor = cursor.next;
            }
 
            if( cursor == cursor.next ) {
                System.out.println( "\n" + cursor.data + " is it!" );
                break;
            } else {
                list.remove( cursor.data );
                System.out.println( "\n" + cursor.data + " is out!" );
            }
        }
    }
}
