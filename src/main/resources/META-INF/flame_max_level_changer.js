var asmapi = Java.type('net.minecraftforge.coremod.api.ASMAPI');
var getMaxLevel = asmapi.mapMethod('func_77325_b');

function initializeCoreMod() {
    return {
        'damage': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.enchantment.FlameEnchantment'
            },
            'transformer': function(classNode) {
                return changeMaxLevel(classNode, 'getFlameMaxLevel');
            }
        }
    }
}

function changeMaxLevel(classNode, methodName) {
    var Opcodes = Java.type('org.objectweb.asm.Opcodes');
    var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    var owner = "com/tome/flamingarrows/Hooks";
    var methods = classNode.methods;
    var method = null;
    for(var i in methods) {
        if(methods[i].name == getMaxLevel) {
            method = methods[i];
            break;
        }
    }
    var target = findFirstInstruction(method, Opcodes.IRETURN);
    method.instructions.remove(target.getNext());
    method.instructions.insertBefore(target, new MethodInsnNode(Opcodes.INVOKESTATIC, owner, methodName, '()I', false));
    return classNode;
}

function findFirstInstruction(method, opcode) {
    var instructions = method.instructions;
    for(var i = 0; i < instructions.size(); i++) {
        var instruction = instructions.get(i);
        if(instruction.getOpcode() == opcode) {
            return instruction;
        }
    }
}
