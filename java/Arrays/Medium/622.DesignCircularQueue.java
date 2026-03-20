class MyCircularQueue {

    int[] arr;
    int front, rear, size;

    public MyCircularQueue(int k) {
        size = k;
        arr = new int[k];
        front = -1;
        rear = -1;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;

        if (isEmpty()) {
            front = 0;
        }

        rear = (rear + 1) % size;
        arr[rear] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;

        if (front == rear) {
            // Only one element
            front = rear = -1;
        } else {
            front = (front + 1) % size;
        }

        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return arr[rear];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }
}