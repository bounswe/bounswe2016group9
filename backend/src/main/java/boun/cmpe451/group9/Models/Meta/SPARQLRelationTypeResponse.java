package boun.cmpe451.group9.Models.Meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by narya on 12/19/16.
 */
public class SPARQLRelationTypeResponse {
    private Head head;
    private Results results;

    public SPARQLRelationTypeResponse() {
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
        private List<Labels> bindings;

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

        public List<Labels> getBindings() {
            return bindings;
        }

        public void setBindings(List<Labels> bindings) {
            this.bindings = bindings;
        }

        public static class Labels{
            private Label label;

            public Labels() {
            }

            public Label getLabel() {
                return label;
            }

            public void setLabel(Label label) {
                this.label = label;
            }


            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Label{
                private String type;
                private String value;

                public Label() {
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
