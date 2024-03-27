import $ from 'jquery';
export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
    },
    getters: {
    },
    mutations: {
        updatetoken(state, token) {
            state.token = token;
        },
        updateuser(state, user) {
            state.id = user.userid;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        logout(state) {
            localStorage.removeItem("token");
            state.id = "";
            state.username = "";
            state.photo = "";
            state.is_login = false;
        }
    },
    actions: {
        login(context, data) {
            $.ajax({
                url: "http://localhost:4000/user/account/login/",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_massage === "success") {
                        localStorage.setItem("token", resp.token);
                        context.commit("updatetoken", resp.token);
                        data.success();
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                    console.log(resp);
                }
            });
        },
        getinfo(context, data) {
            $.ajax({
                url: "http://localhost:4000/user/account/getinfo/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    context.commit("updateuser", {
                        ...resp,
                        is_login: true,
                    });
                    data.success(resp);
                },
                error(resp) {
                    data.success(resp);
                }

            });
        }
    },
    modules: {
    }
}
