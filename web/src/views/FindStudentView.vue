<template>
    <MyContainer>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        学号
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        专业
                    </th>
                    <th>
                        班级
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>

                    <td>
                        0
                    </td>
                    <td>
                        <input v-model="gid" type="text" class="form-control" id="idname">
                    </td>
                    <td>
                        <input v-model="gstuname" type="text" class="form-control" id="stuname">
                    </td>
                    <td>
                        <select class="form-select" aria-label="Default select example" id="majorname2"
                            v-model="gmajor">
                            <option value="allmajorname">
                                所有专业
                            </option>
                            <option v-for="(major) in majornames" :key="major" :value="major">
                                {{ major }}
                            </option>
                        </select>
                    </td>
                    <td>
                        <select class="form-select" aria-label="Default select example" id="classname2"
                            v-model="gclassname">
                            <option value="allclassname">
                                所有班级
                            </option>
                            <option v-for="(classname) in clnames" :key="classname" :value="classname">
                                {{ classname }}
                            </option>
                        </select>
                    </td>
                    <td>
                        <button type="button" class="btn btn-secondary" @click="getlist">查询</button>
                        <button type="button" class="btn btn-primary has-left" data-bs-toggle="modal"
                            data-bs-target="#addlabel">添加</button>



                        <!--添加Modal-->
                        <div class="modal fade" id="addlabel" tabindex="-1" aria-labelledby="addlabel"
                            aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addlabel">添加学生</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <form @submit.prevent="add">
                                        <div class="modal-body">


                                            <div class="mb-3">
                                                <label for="exampleInputPassword1" class="form-label">姓名</label>
                                                <input v-model="addstuname" type="text" class="form-control"
                                                    id="exampleInputPassword1">
                                            </div>
                                            <div class="mb-3">
                                                <label for="classname1" class="form-label">班级</label>
                                                <select class="form-select" aria-label="Default select example"
                                                    id="classname1" v-model="addclassname">
                                                    <option v-for="(classname, index) in clnames" :key="index"
                                                        :value="classname">
                                                        {{ classname }}
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="major1" class="form-label">专业</label>
                                                <select class="form-select" aria-label="Default select example"
                                                    id="major1" v-model="addmajor">
                                                    <option v-for="(majorname, index) in majornames" :key="index"
                                                        :value="majorname">
                                                        {{ majorname }}

                                                    </option>
                                                </select>
                                            </div>




                                        </div>
                                        <div class="modal-footer">
                                            <div class="errormassage">
                                                {{ error_massage }}
                                            </div>
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">添加</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!--添加Modal-->
                    </td>

                </tr>

                <tr v-for="(student, index) in students" :key="student.id">
                    <td>
                        {{ index + 1 }}
                    </td>
                    <td>
                        {{ student.id }}
                    </td>
                    <td>
                        {{ student.studentName }}
                    </td>
                    <td>
                        {{ student.major }}
                    </td>
                    <td>
                        {{ student.className }}
                    </td>
                    <td>
                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal"
                            :data-bs-target="'#updatelabel' + student.id">修改</button>
                        <button type="button" class="btn btn-danger has-left" data-bs-toggle="modal"
                            data-bs-target="#deletelabel" @click="updatedateleid(student.id)">删除</button>
                        <!--deleteModal-->
                        <div class="modal fade" id="deletelabel" tabindex="-1" aria-labelledby="deletelabel"
                            aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="deletelabel">
                                            <i class="bi bi-exclamation-octagon">
                                                <img src="../assets/exclamation-octagon.svg">
                                            </i>
                                            warning
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        确认删除此条记录？
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary"
                                            @click="deletestu(now_delete_id)">确认删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--deleteModal-->
                        <!--updateModal-->
                        <div class="modal fade" :id="'updatelabel' + student.id" tabindex="-1"
                            :aria-labelledby="'updatelabel' + student.id" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" :id="'updatelabel' + student.id">Modal title</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <form @submit.prevent="updatestu(student)">
                                        <div class="modal-body">


                                            <div class="mb-3">
                                                <label for="exampleInputPassword1" class="form-label">姓名</label>
                                                <input v-model="student.studentName" type="text" class="form-control"
                                                    id="exampleInputPassword1">
                                            </div>
                                            <div class="mb-3">
                                                <label for="classname1" class="form-label">班级</label>
                                                <select class="form-select" aria-label="Default select example"
                                                    id="classname1" v-model="student.className">
                                                    <option v-for="(classname, index) in clnames" :key="index"
                                                        :selected="classname == student.className ? true : false"
                                                        :value="classname">
                                                        {{ classname }}
                                                    </option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="major1" class="form-label">专业</label>
                                                <select class="form-select" aria-label="Default select example"
                                                    id="major1" v-model="student.major">
                                                    <option v-for="(majorname, index) in majornames" :key="index"
                                                        :selected="majorname == student.major ? true : false"
                                                        :value="majorname">
                                                        {{ majorname }}

                                                    </option>
                                                </select>
                                            </div>




                                        </div>
                                        <div class="modal-footer">
                                            <div class="errormassage">
                                                {{ error_massage }}
                                            </div>
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!--updateModal-->
                    </td>
                </tr>
            </tbody>
        </table>
    </MyContainer>
</template>
<script>
import MyContainer from '@/components/MyContainer.vue';
import $ from 'jquery';
import { ref } from 'vue';
import { useStore } from 'vuex';
import { Modal } from 'bootstrap/dist/js/bootstrap';
export default {
    name: "FindStudentView",
    components: {
        MyContainer,
    },
    setup() {
        let students = ref([]);
        let clnames = ref([]);
        let majornames = ref([]);
        let now_delete_id = ref('');
        let error_massage = ref('');
        let gid = ref('');
        let gstuname = ref('');
        let gclassname = ref('');
        let gmajor = ref('');
        let addstuname = ref('');
        let addclassname = ref('');
        let addmajor = ref('');
        const store = useStore();
        const updatedateleid = (temp) => {
            now_delete_id.value = temp;
        }
        const add = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    student_name: addstuname.value,
                    class_name: addclassname.value,
                    major: addmajor.value,
                },
                success(resp) {
                    if (resp.error_massage === "success") {
                        getlist();
                        console.log(resp);
                        Modal.getInstance("#addlabel").hide();
                    } else {
                        error_massage.value = resp.error_massage;
                        console.log("失败");
                    }
                },
            });
        }
        const updatestu = (student) => {
            $.ajax({
                url: "http://localhost:4000/student/select/update/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    id: student.id,
                    student_name: student.studentName,
                    class_name: student.className,
                    major: student.major,
                },
                success(resp) {
                    if (resp.error_massage === "success") {

                        console.log(resp);
                        Modal.getInstance("#updatelabel" + student.id).hide();
                    } else {
                        error_massage.value = resp.error_massage;
                        console.log("失败");
                    }
                },
            });
        }
        const deletestu = (now_delete_id) => {
            $.ajax({
                url: "http://localhost:4000/student/select/delete/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    id: now_delete_id,
                },
                success(resp) {
                    if (resp.error_massage === "success") {
                        getlist();
                        console.log(resp);
                        Modal.getInstance("#deletelabel").hide();
                    } else {
                        console.log("失败");
                    }
                },
            });
        }
        const getlist = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/getlist/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    student_id: gid.value,
                    student_name: gstuname.value,
                    class_name: gclassname.value,
                    major: gmajor.value,
                },
                success(resp) {
                    console.log(gid.value);
                    students.value = resp;
                    console.log(resp);
                },
                error(resp) {
                    console.log(resp);
                }
            });

        }
        const getclnames = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/getclassnames/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    clnames.value = resp;
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }
        const getmajornames = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/getmajornames/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    majornames.value = resp;
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }
        getclnames();
        getmajornames();
        getlist();

        return {
            getlist,
            deletestu,
            updatedateleid,
            updatestu,
            getclnames,
            getmajornames,
            add,
            gid,
            gstuname,
            gclassname,
            gmajor,
            now_delete_id,
            students,
            store,
            clnames,
            majornames,
            error_massage,
            addclassname,
            addmajor,
            addstuname,
        }
    }
}
</script>
<style scoped>
.has-left {
    margin-left: 5px;
}

.errormassage {
    color: red;
}
</style>