<template>
    <MyContainer>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username">
                    </div>
                    <div class="mb-3">
                        <label for="password1" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password1">
                    </div>
                    <div class="errormassage">{{ error_massage }}</div>
                    <button type="submit" class="btn btn-primary mybutton">登录</button>
                </form>
            </div>
        </div>

    </MyContainer>

</template>
<script>
import MyContainer from "@/components/MyContainer.vue";
import { ref } from "vue";
import { useStore } from "vuex";
import router from '../router/index';
export default {
    components: {
        MyContainer,
    },
    setup() {
        let username = ref('');
        let password = ref('');
        let error_massage = ref('');
        const store = useStore();
        let jwt_token = localStorage.getItem("token");
        if (jwt_token) {
            store.commit("updatetoken", jwt_token);
            store.dispatch("getinfo", {
                success(resp) {
                    console.log(resp);
                    router.push({ name: "home" });
                },
                error(resp) {
                    console.log(resp);
                }
            });

        }
        const login = () => {
            error_massage.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {

                    store.dispatch("getinfo", {
                        success(resp) {
                            router.push({ name: "home" });
                            console.log(resp);
                        },
                        error(resp) {
                            console.log(resp);
                        }
                    });

                },
                error(resp) {
                    error_massage.value = "用户名或密码错误";
                    console.log(resp);
                }
            });
        }
        return {
            username,
            password,
            error_massage,
            store,
            login,
        }
    }
}
</script>
<style scoped>
.mybutton {
    width: 100%;
}

.errormassage {
    color: red;
}
</style>