<style scoped>
    .logs {
        background: black;
        color: white;
        padding: 13px;
        height: 600px;
        overflow-y: auto;
    }
</style>
<template>
    <div class="logs">
        <p>{{ logs }}</p>
    </div>
</template>
<script>
export default {
    props: {
        logs: Object
    }
}
</script>