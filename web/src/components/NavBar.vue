<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{ name: 'home' }">学生管理系统</router-link>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <router-link :class="rout_name == 'home' ? 'nav-link activeli' : 'nav-link'" :to="{ name: 'home' }">
                        学生查询</router-link>
                </li>
                <li class="nav-item">
                    <router-link :class="rout_name == 'queryscore' ? 'nav-link activeli' : 'nav-link'"
                        :to="{ name: 'queryscore' }">成绩查询</router-link>
                </li>
            </ul>
            <ul v-if="!$store.state.user.is_login" class="navbar-nav">
                <li class="nav-item">
                    <router-link :to="{ name: 'login' }" class="nav-link">登录</router-link>
                </li>
                <li class="nav-item">
                    <router-link :to="{ name: 'register' }" class="nav-link">注册</router-link>
                </li>
            </ul>
            <ul v-if="$store.state.user.is_login" class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        {{ store.state.user.username }}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">我的</a></li>
                        <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</template>
<script>
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";
export default {
    name: "NavBar",
    setup() {
        const route = useRoute();
        const store = useStore();
        let rout_name = computed(() => route.name);
        const logout = () => {
            store.commit("logout");
        }
        return {
            route,
            rout_name,
            store,
            logout,
        }

    }
}
</script>
<style scoped>
.activeli {
    filter: brightness(500%);
    color: lightgray;
}

.myend {
    margin-left: 10px;
}
</style>