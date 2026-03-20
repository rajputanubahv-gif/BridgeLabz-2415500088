import java.util.Scanner;

class QueueArray {
    int size;
    int front, rear, count;
    int arr[];

    QueueArray(int n) {
        size = n;
        arr = new int[size];
        front = -1;
        rear = -1;
        count = 0;
    }

    boolean isFull() {
        return count == size;
    }

    boolean isEmpty() {
        return count == 0;
    }

    void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }
        if (front == -1) front = 0;
        rear++;
        arr[rear] = x;
        count++;
        System.out.println(x + " inserted");
    }

    void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return;
        }
        System.out.println(arr[front] + " removed");
        front++;
        count--;
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front; i <= rear; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Current size: " + count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of queue: ");
        int n = sc.nextInt();

        QueueArray q = new QueueArray(n);

        while (true) {
            System.out.println("\n1.Enqueue  2.Dequeue  3.Display  4.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter value: ");
                    int x = sc.nextInt();
                    q.enqueue(x);
                    break;

                case 2:
                    q.dequeue();
                    break;

                case 3:
                    q.display();
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}