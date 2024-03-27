<template>
    <div class="container has-top">
        <div class="card">
            <div class="card-body">
                <slot>

                </slot>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "MyContainer",
}
</script>
<style scoped>
.has-top {
    margin-top: 20px;
}
</style>