package ani.rss.action;

import ani.rss.annotation.Auth;
import ani.rss.annotation.Path;
import ani.rss.entity.Ani;
import ani.rss.entity.Result;
import ani.rss.util.TmdbUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;

import java.io.IOException;
import java.util.Map;

/**
 * TMDB
 */
@Auth
@Path("/tmdb")
public class ThemoviedbAction implements BaseAction {
    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        String s = request.getParam("method");
        if ("getThemoviedbName".equals(s)) {
            Ani ani = getBody(Ani.class);
            String themoviedbName = TmdbUtil.getName(ani);
            Result<Ani> result = new Result<Ani>()
                    .setCode(HttpStatus.HTTP_OK)
                    .setMessage("获取TMDB成功")
                    .setData(ani.setThemoviedbName(themoviedbName));
            if (StrUtil.isBlank(themoviedbName)) {
                result.setCode(HttpStatus.HTTP_INTERNAL_ERROR)
                        .setMessage("获取TMDB失败");
            }
            result(result);
        }
    }
}
