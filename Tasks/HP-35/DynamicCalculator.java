public class DynamicCalculator {
    Item [] expr;
    int ip;
    DynamicStack stack;

    public DynamicCalculator(Item[] expr){
        this.expr = expr;
        this.ip = 0;
        this.stack = new DynamicStack();
    }

    public int run(){
        while(ip < expr.length){
            step();
        }
        return stack.pop();
    }

    public void step(){
        Item nxt = expr[ip++];

        switch(nxt.getType()){

            case ADD: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
                break;
            }
            case SUB: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x - y);
                break;
            }
            case MUL: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
                break;
            }
            case DIV: {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x / y);
                break;
            }
            case VALUE: {
                stack.push(nxt.getValue());
            }
        }
    }
}
