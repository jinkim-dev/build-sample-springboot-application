<template>
    <v-data-table
        :headers="headers"
        :items="items"
        :items-per-page="5"
    >
        <template
        slot="headerCell"
        slot-scope="{ header }"
        >
        <span
            class="subheading font-weight-light text-success text--darken-3"
            v-text="header.text"
        />
        </template>
        <template
        slot="items"
        slot-scope="{ item }"
        >
        <td>{{ item.displayName }}</td>
        <td>{{ item.building? 'Building' : 'Finished' }}</td>
        <td>{{ item.duration / 1000 + ' sec' }}</td>
        <td>{{ item.result }}</td>                
        </template>
    </v-data-table>
</template>
<script>
export default {
    props: {
        items: Object
    }
}
</script>