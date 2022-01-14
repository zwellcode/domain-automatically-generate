package cn.zwellcode.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result error(String msg){
        return new Result(500, msg, null);
    }
    public static <T>  Result success(T data){
        return new Result(200, null, data);
    }
    public static <T>  Result success(){
        return new Result(200, null, null);
    }

}
