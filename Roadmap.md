https://mermaid-js.github.io/mermaid-live-editor
```mermaid
gantt
    dateFormat YYYY-MM-DD
    title PanGu Roadmap
    section init frame
    archetype: active, 1_1, 2021-09-04, 15d
    section build frame
    concept: active, 2_1, after 1_1, 30d
    config: active, 2_2, after 1_1, 30d
    event: 2_3, after 1_1, 30d
    concurrent: 2_4, after 1_1, 30d
    server: 2_5, after 1_1, 30d
    cache: 2_6, after 1_1, 30d
    io: 2_7, after 1_1, 30d
    section build web solution
    net: 3_1, after 2_1, 30d
    rbac: 3_1, after 2_1, 30d
```