package forum.kaoyan.qinghuagong.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换一个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }


}
