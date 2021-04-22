public class NewCollection implements CustomCollection {
    private LLString head;
    private LLString tail;
    private int size = 0;


    public NewCollection() {
        this.head = null;
        this.tail = null;
    }

    public void setHead(LLString newHead) {
        this.head = newHead;
    }

    public LLString getHead() {
        return this.head;
    }

    public void setTail(LLString newTail) {
        this.tail = newTail;
        this.tail.setTail(true);
    }

    public LLString getTail() {
        return this.tail;
    }

    public int size() {
        return this.size;
    }

    public boolean add(String data) {
        if (data == null){
            return false;
        }
        LLString newLLString = new LLString(data);
        this.size++;
        if (this.head == null) {
            this.head = newLLString;
            this.tail = newLLString;
        } else {
            LLString oldTail = this.tail;
            this.tail = newLLString;
            this.tail.setNext(this.head);
            this.head.setPrevious(this.tail);
            oldTail.setNext(this.tail);
            oldTail.setTail(false);
            oldTail.setPrevious(this.head);
        }
        this.tail.setTail(true);
        return true;
    }

    public boolean addAll(String[] strArr) {
        if (strArr == null){
            return false;
        }
        for (String s : strArr) {
            this.add(s);
        }
        return true;
    }

    public boolean addAll(NewCollection coll) {
        if (coll == null){
            return false;
        }
        LLString current = coll.head;
        this.add(current.getData());
        while (!current.checkIfTail()) {
            this.add(current.getNext().getData());
            current = current.getNext();
        }
        return true;
    }

    public boolean delete(String data) {
        LLString current = this.head;
        if (this.head.getData().equals(data)) {
            this.head = this.head.getNext();
            this.head.setPrevious(this.tail);
            this.tail.setNext(this.head);
            this.size--;
            return true;
        }
        while (!current.checkIfTail()) {
            if (current.getData().equals(data)) {
                current.getNext().setPrevious(current.getPrevious());
                current.getPrevious().setNext(current.getNext());
                current.setPrevious(null);
                current.setNext(null);
                this.size--;
                return true;
            }
            current = current.getNext();
        }
        if (current.checkIfTail()) {
            if (current.getData().equals(data)) {
                this.tail = current.getPrevious();
                this.tail.setNext(this.head);
                this.tail.setTail(true);
                this.head.setPrevious(this.tail);
                this.size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(String data) {
        LLString current = this.head;
        if (this.head.getData().equals(data)) {
            return true;
        }
        while (!current.checkIfTail()) {
            if (current.getNext().getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean clear() {
        this.tail = null;
        this.head = null;
        this.size = 0;
        return true;
    }

    public boolean trim() {
        System.out.println("this method does nothing and always returns true");
        return true;
    }


    public void printAll() {
        if (this.head == null) {
            System.out.println("There are no elements");
            return;
        }
        LLString current = this.head;
        System.out.println(this.head.getData());
        while (!current.checkIfTail()) {
            System.out.println(current.getNext().getData());
            current = current.getNext();
        }
    }

    public String get(int index) {
        LLString current = this.head;
        int counter = 0;
        while (!current.checkIfTail()) {
            if (counter == index) {
                return current.getData();
            }
            counter++;
            current = current.getNext();
        }
        return null;
    }

    public boolean compare(NewCollection coll) {
        if (this.size != coll.size()) {
            return false;
        }
        if (this.size == 0 && coll.size == 0) {
            return true;
        }
        LLString currentOne = this.head;
        LLString currentTwo = coll.head;
        if (!this.tail.getData().equals(coll.tail.getData())) {
            return false;
        }
        while (!currentOne.checkIfTail()) {
            if (!currentOne.getData().equals(currentTwo.getData())) {
                return false;
            }
            currentOne = currentOne.getNext();
            currentTwo = currentTwo.getNext();
        }
        return true;
    }

}
