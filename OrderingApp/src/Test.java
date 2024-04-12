/**
 * @author hmh
 * @date 2024/4/6
 * @desc
 */
public class Test {
    public static void main(String[] args) {

        OrderingService service = new OrderingService();
        Order[] menu = service.menu;
        Order[] order = service.order;

        System.out.printf("%-4s%-6s\t%-3s\t%-3s\n", "序号","菜单名称", "单价", "点赞数");
        service.initial();
        for (Order menu1 : menu) {
            System.out.print(menu1.toString());
        }
        System.out.printf("\n%-4s%-3s\t%-7s\t%5s\t%8s\t%5s\t%6s\n", "序号","订餐人", "订餐及份数", "日期", "送餐地址", "状态", "价格");
        for (Order order1 : order) {
            if (order1 != null)
                System.out.print(order1.toString1());
        }
    }
}
