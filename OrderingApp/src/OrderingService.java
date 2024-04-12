import java.util.Scanner;

/**
 * @author hmh
 * @date 2024/4/6
 * @desc
 */
public class OrderingService {
    Scanner scanner = new Scanner(System.in);

    Order[] menu = new Order[4];
    Order[] order = new Order[5];

    /**
     * 初始化订单信息和菜单信息
     */
    public void initial() {
        menu[0] = new Order(1, "黄焖鸡", 25.0, 0);
        menu[1] = new Order(2, "鱼香肉丝", 20.0, 0);
        menu[2] = new Order(3, "麻婆豆腐", 10.0, 0);
        menu[3] = new Order(4, "清炒土豆丝", 8.0, 0);

        // 初始化菜单信息
        order[0] = new Order(1, "托尼", "黄焖鸡 2份", 3, "苏源路5号", "1", 50.0);
        order[1] = new Order(2, "胡阿由", "鱼香肉丝 2份", 3, "苏源路5号", "0", 45.0);

    }

    /**
     * 输出菜单信息
     */
    public void menuMessege() {
        System.out.printf("%-4s%-6s\t%-3s\t%-3s\n", "序号", "菜单名称", "单价", "点赞数");
        for (Order menu1 : menu) {
            System.out.print(menu1.toString());
        }
    }

    /**
     * 实现菜单切换
     */
    public void startMenu() {
        System.out.println("欢迎使用【吃了吗订餐系统】");
        System.out.println("*************************************");
        System.out.println("1. 我要订餐");
        System.out.println("2. 查看餐袋");
        System.out.println("3. 签收订单");
        System.out.println("4. 删除订单");
        System.out.println("5. 我要点赞");
        System.out.println("6. 退出系统");
        System.out.println("*************************************");
        System.out.print("请选择：");
        int opt = scanner.nextInt();
        do {
            switch (opt) {
                case 0:
                    startMenu();
                    break;
                case 1:
                    add();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    sign();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    praise();
                    break;
                case 6:
                    System.out.println("谢谢使用，欢迎下次光临!");
                    return;
                default:
                    System.out.print("Error,请重新输入:");
                    break;
            }
            opt = scanner.nextInt();
        } while (opt != 6);
    }

    /**
     * 查看餐袋
     */
    public void display() {
        System.out.println("***** 查看餐袋 *****");
        System.out.printf("%-4s%-3s\t%-7s\t%5s\t%8s\t%5s\t%6s\n", "序号", "订餐人", "订餐及份数", "日期", "送餐地址", "状态", "价格");
        for (Order order1 : order) {
            if (order1 != null) {
                if (order1.status.equals("0")) {
                    order1.status = "已完成";
                } else if (order1.status.equals("1")) {
                    order1.status = "已预订";
                }
                System.out.print(order1.toString1());
            }
        }
        System.out.print("输入0返回：");
    }

    /**
     * 增加订单信息
     */
    public void add() {
        System.out.println("*****  我要订餐 *****");
        if (close() == 5){
            System.out.println("本店已打烊...");
            return;
        }
        System.out.print("请输入订餐人姓名：");
        String name = scanner.next();
        menuMessege();
        System.out.print("请输入您要点的菜品编号：");
        int no = scanner.nextInt();
        System.out.print("请选择您需要的份数：");
        int num = scanner.nextInt();
        System.out.print("请输入送餐时间（送餐时间是10点至20点间整点送餐）：");
        int date = scanner.nextInt();
        System.out.print("请输入送餐地址：");
        String addr = scanner.next();
        System.out.println("订餐成功！");
        System.out.println("您订的是：" + menu[no - 1].dishName + " " + num + "份");
        System.out.println("送餐时间：" + date + "时");
        double price = num * menu[no - 1].unitPrice;
        if (price < 50) {
            price = price + 5;
            System.out.println("餐费：" + (price - 5) + "元，送餐费：5.0元，总计：" + price + "元。");
        } else {
            System.out.println("餐费：" + price + "元。");
        }

        for (int i = 0; i < order.length; i++) {
            if (order[i] == null) {
                order[i] = new Order((i + 1), name,
                        (menu[no - 1].dishName + " " + num + "份"), date, addr, "1", price);
                break;
            }
        }

        System.out.print("输入0返回：");
    }

    /**
     * 实现签收订单
     */
    public void sign() {
        System.out.println("***** 签收订单 *****");
        System.out.print("请选择要签收的订单序号：");
        int number = scanner.nextInt();
        int flag = -2;
        for (int i = 0; i < order.length; i++) {
            if (order[i] != null) {
                if (number != order[i].no) {
                    flag--;
                } else if ("1".equals(order[i].status) || "已预订".equals(order[i].status)) {
                    // 订单号存在，且订单状态为已预订
                    flag = i;
                    break;
                } else { // 订单号存在，但是已完成
                    flag = -1;
                    break;
                }
            }
//            break;
        }
        if (flag < -2) {
            System.out.println("您选择的订单不存在！");
        } else if (flag == -1) {
            System.out.println("您的订单已完成！");
        } else {
            order[flag].status = "0"; // 修改订单状态为已完成"0"
            System.out.println("订单签收成功！");
            // todo: 修改订单状态失败...
        }

        System.out.print("输入0返回：");
    }

    /**
     * 实现删除订单记录
     */
    public void delete() {
        System.out.println("***** 删除订单 *****");
        System.out.print("请输入要删除的订单序号：");
        int number = scanner.nextInt();
        int flag = 10000;
//        int index = 0;// 记录order中记录的数量
        for (int i = 0; i < order.length; i++) {
//            index++;
            if (order[i] != null) {
                if (number == order[i].no) { // 订单号存在
                    if ("1".equals(order[i].status) || "已预订".equals(order[i].status)) {
                        // 订单状态为已预订
                        flag = -2;
                    } else flag = i; // 可以删除
                    break;
                } else { // 订单号不存在
                    flag = -1;
                }
            }
        }
        if (flag == -1) {
            System.out.println("您要删除的订单不存在！");
        } else if (flag == -2) {
            System.out.println("您选择的订单未签收，不能删除！");
        } else if (flag < 5) {
            System.out.println("删除订单成功！");
            for (int i = flag; i < order.length - 1; i++) {
                order[i] = order[i + 1];
            }
        }

        System.out.print("输入0返回：");

    }

    /**
     * 实现点赞功能
     */
    public void praise() {
        System.out.println("***** 我要点赞 *****");
        menuMessege();
        System.out.print("请选择您要点赞的菜品序号：");
        int number = scanner.nextInt();
        int flag = -2;
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] != null) {
                if (number == menu[i].no) {
                    System.out.println("点赞成功！");
                    flag = i;
                    break;
                } else {
                    flag = -1;
                }
            }
        }
        if (flag == -1) {
            System.out.println("您要点赞的菜品不存在！");
        } else {
            menu[flag].likeNum++;
        }
        System.out.print("输入0返回：");

    }

    /**
     * 如果订单信息超过5条，打烊
     */
    public int close() {
        int index = 0;// 记录order记录数
        for (int i = 0; i < order.length; i++) {
            if (order[i] != null) {
                index++;
            }
        }
        return index;
    }

}
