class MyException extends RuntimeException {
    private String info;
    MyException(String string) {
        info = string;
    }

    public void printInfo(){
        System.out.println(info);
    }
}
