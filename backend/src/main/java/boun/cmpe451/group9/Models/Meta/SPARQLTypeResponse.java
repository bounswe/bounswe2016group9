package boun.cmpe451.group9.Models.Meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


public class SPARQLTypeResponse {
    private Head head;
    private Results results;

    public SPARQLTypeResponse(){

    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    private static class Head{
        private List<String> link;
        private List<String> vars;

        public Head() {
        }

        public List<String> getLink() {
            return link;
        }

        public void setLink(List<String> link) {
            this.link = link;
        }

        public List<String> getVars() {
            return vars;
        }

        public void setVars(List<String> vars) {
            this.vars = vars;
        }
    }

    public static class Results{
        private boolean distinct;
        private boolean ordered;
        private List<Types> bindings;

        public Results() {
        }

        public boolean isDistinct() {
            return distinct;
        }

        public void setDistinct(boolean distinct) {
            this.distinct = distinct;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public List<Types> getBindings() {
            return bindings;
        }

        public void setBindings(List<Types> bindings) {
            this.bindings = bindings;
        }

        public static class Types{
            private Type types;

            public Types(){

            }

            public Type getTypes() {
                return types;
            }

            public void setTypes(Type types) {
                this.types = types;
            }

            public static class Type{
                private String type;
                private String value;

                public Type() {
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
