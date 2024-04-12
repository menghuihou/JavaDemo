/**
 * @author hmh
 * @date 2024/4/6
 * @desc
 */
public class OrderingClient {
    public static void main(String[] args) {
        OrderingService service = new OrderingService();
        service.initial();
        service.startMenu();
    }
}
