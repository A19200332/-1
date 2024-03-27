<template>
    <MyContainer>
        <div class="row">
            <div class="col">
                <input type="text" v-model="stu_id" placeholder="学号">
            </div>
            <div class="col">
                <input type="text" v-model="stu_name" placeholder="姓名">
            </div>
            <div class="col">
                <select class="form-select form-select-sm" aria-label=".form-select-sm example" v-model="stu_major">
                    <option selected :value="''">专业</option>
                    <option v-for="(major, index) in majors" :key="index" :value="major">{{ major }}</option>

                </select>
            </div>
            <div class="col">
                <select class="form-select form-select-sm" aria-label=".form-select-sm example" v-model="stu_class">

                    <option selected :value="''">班级</option>
                    <option v-for="(classs, index) in classes" :key="index" :value="classs">{{ classs }}</option>
                </select>
            </div>
            <div class="col">
                <button type="button" class="btn btn-secondary" @click="getlist">查询</button>
                <button type="button" class="btn btn-primary" @click="addscore">添加</button>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>学号</th>
                            <th v-for="(course, index) in courses" :key="index">{{ course }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(person, index) in persons" :key="person.id">
                            <td>{{ index + 1 }}</td>
                            <td>{{ person.id }}</td>
                            <td v-for="course in courses" :key="course">
                                <div v-if="person.mp.has(course)">{{ person.course_list[person.mp.get(course)].score }}
                                </div>
                                <div v-else>null</div>
                            </td>

                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </MyContainer>
</template>
<script>
import MyContainer from '@/components/MyContainer.vue';
// import { reactive } from 'vue';
import { ref } from 'vue';
import $ from 'jquery';
import { useStore } from 'vuex';
export default {
    name: "QueryScoreView",
    components: {
        MyContainer,
    },
    setup() {
        const store = useStore();
        let students = ref([]);
        let classes = ref([]);
        let majors = ref([]);
        let courses = ref([]);
        let persons = ref([]);
        let stu_id = ref('');
        let stu_name = ref('');
        let stu_major = ref('');
        let stu_class = ref('');
        let person_map = new Map;
        const get_classes = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/getclassnames/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    classes.value = resp;
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }
        get_classes();
        const get_majors = () => {
            $.ajax({
                url: "http://localhost:4000/student/select/getmajornames/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    majors.value = resp;
                },
                error(resp) {
                    console.log(resp);
                }
            });
        }
        get_majors();
        const get_courses = () => {
            courses.value = [];
            for (let i of students.value) {
                let f = true;
                for (let j of courses.value) {
                    if (i.course === j) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    courses.value.push(i.course);
                }
            }
        }
        const get_persons = () => {
            persons.value = [];
            let index = 0;
            person_map = new Map;
            for (let i of students.value) {
                if (!person_map.has(i.studentClassId)) {
                    let map = new Map;
                    map.set(i.course, 0)
                    persons.value.push({
                        id: i.studentClassId,
                        course_index: 0,
                        mp: map,
                        course_list: [
                            {
                                course: i.course,
                                score: i.score,
                            },
                        ]
                    });
                    person_map.set(i.studentClassId, index);
                    index++;
                }
                else {
                    persons.value[person_map.get(i.studentClassId)].course_list.push({
                        course: i.course,
                        score: i.score,
                    });

                    persons.value[person_map.get(i.studentClassId)].course_index++;
                    persons.value[person_map.get(i.studentClassId)].mp.set(i.course, persons.value[person_map.get(i.studentClassId)].course_index);

                }
            }
        }
        const getlist = () => {
            $.ajax({
                url: "http://localhost:4000/student/score/getlist/",
                type: "post",
                data: {
                    stu_id: stu_id.value,
                    stu_name: stu_name.value,
                    class_name: stu_class.value,
                    major: stu_major.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    students.value = resp;
                    get_courses();
                    get_persons();
                    console.log(resp);
                },
                error(resp) {
                    console.log(resp);
                }
            });

        }

        return {
            store,
            students,
            classes,
            majors,
            stu_class,
            stu_id,
            stu_major,
            stu_name,
            courses,
            persons,
            get_classes,
            get_majors,
            getlist,
            get_courses,
            get_persons,
        }
    }
}
</script>
<style scoped>
</style>